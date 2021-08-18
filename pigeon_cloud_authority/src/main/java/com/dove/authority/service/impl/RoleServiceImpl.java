package com.dove.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.authority.Async.AuthorityAsync;
import com.dove.authority.entity.Role;
import com.dove.authority.entity.bo.RoleBo;
import com.dove.authority.entity.vo.RoleVo;
import com.dove.authority.mapper.PermissionMapper;
import com.dove.authority.mapper.RoleMapper;
import com.dove.authority.mapper.UserMapper;
import com.dove.authority.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.authority.utils.ConvertUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import com.dove.entity.UserDetailsImpl;
import com.dove.util.SecurityContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.pkcs11.P11Util;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private AuthorityAsync authorityAsync;

    @Autowired
    private ConvertUtil convertUtil;

    @Override
    @Transactional
    public boolean updateRole(RoleBo role) {

        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        int weightest = roleMapper.getWeightestOfUser(userDetails.getId());

        int roleWeight = roleMapper.getWeightOfRole(role.getId());

        if (weightest >= roleWeight){
            throw new GlobalException(Result.error("无法修改权重大于等于自身的角色"));
        }

        boolean isSuccess = roleMapper.updateRole(role, userDetails.getEnterpriseId()) > 0;

        if (!isSuccess){
            return false;
        }

        List<Long> originPermission = permissionMapper.getPermissionIdOfRole(role.getId());
        List<Long> newPermission = role.getPermissionIds();
        List<Long> publicPermission = originPermission.stream()
                                                .filter(permission ->  newPermission.contains(permission))
                                                .collect(Collectors.toList());

        //求出两个集合的左右差集
        originPermission.removeAll(publicPermission);
        newPermission.removeAll(publicPermission);

        Future<String> addFuture = null;

        //如果权限改变
        if (CollectionUtils.isNotEmpty(originPermission) || CollectionUtils.isNotEmpty(newPermission)){
            if (CollectionUtils.isNotEmpty(newPermission)){
                addFuture = authorityAsync.addPermissionOfRole(newPermission, role.getId());
            }
            if (CollectionUtils.isNotEmpty(originPermission)){
                permissionMapper.deletePermissionOfRole(originPermission, role.getId());
            }
            List<Long> userIds = userMapper.findUserOfRole(role.getId());
            CountDownLatch count = new CountDownLatch(userIds.size());
            userIds.forEach(userId -> authorityAsync.updateAuthorityOfUser(userId, count));

            try {
                //等待redis数据更新完成
                count.await();
            } catch (InterruptedException e) {
                throw new GlobalException(Result.error("修改失败"));
            }

            if (addFuture == null){
                return true;
            }

            //等待数据库数据更新完成
            while (!addFuture.isDone()){
                Thread.yield();
            }
        }

        return true;
    }

    @Override
    public boolean createRole(Role role) {
        Integer roleWeight = role.getWeight();
        if (roleWeight < 1 || roleWeight > 99){
            throw new GlobalException(Result.error("角色权重参数不合法"));
        }

        UserDetailsImpl details = SecurityContextUtil.getUserDetails();

        int weightest = roleMapper.getWeightestOfUser(details.getId());

        if (weightest >= roleWeight){
            throw new GlobalException(Result.error("无法创建权重大于等于自己的角色"));
        }

        boolean isExist = roleMapper.getCountByNameAndEnterprise(role.getName(), details.getEnterpriseId()) > 0;
        if (isExist){
            throw new GlobalException(Result.error("该角色名已存在"));
        }

        role.setEnterpriseId(details.getEnterpriseId());

        return roleMapper.insert(role) > 0;
    }

    @Override
    @Transactional
    public boolean deleteRole(Long roleId) {
        UserDetailsImpl details = SecurityContextUtil.getUserDetails();

        int weightest = roleMapper.getWeightestOfUser(details.getId());

        int roleWeight = roleMapper.getWeightOfRole(roleId);

        if (weightest >= roleWeight){
            throw new GlobalException(Result.error("无法删除权重大于等于自身的角色"));
        }

        boolean isSuccess = roleMapper.deleteRoleByIdAndEnterprise(roleId, details.getEnterpriseId()) > 0;
        if (!isSuccess){
            return false;
        }

        //删除角色对应的权限连接信息
        permissionMapper.deletePermissionOfRole(roleId);

        List<Long> userIds = userMapper.findUserOfRole(roleId);

        CountDownLatch count = new CountDownLatch(userIds.size());

        //更新该角色对应所有角色的权限信息
        userIds.forEach(userId -> authorityAsync.updateAuthorityOfUser(userId, count));

        try {
            count.await();
        } catch (InterruptedException e) {
            throw new GlobalException(Result.error("修改失败"));
        }

        return true;
    }

    @Override
    public IPage<RoleVo> getRole(Integer page, Integer size) {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        Long enterpriseId = userDetails.getEnterpriseId();

        Page<Role> rolePage = new Page<>(page,size);

        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("enterprise_id", enterpriseId);


        return convertUtil.convert(roleMapper.selectPage(rolePage, wrapper), RoleVo.class);
    }

    @Override
    public List<RoleVo> getRole() {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        return roleMapper.findRoleVoOfEnterprise(userDetails.getEnterpriseId());
    }

    @Override
    @Transactional
    public boolean updateRoleOfUser(List<Long> roleIds, Long userId) {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        int myWeightest = roleMapper.getWeightestOfUser(userDetails.getId());
        int weightestOfUser = roleMapper.getWeightestOfUser(userId);

        if (myWeightest >= weightestOfUser){
            throw new GlobalException(Result.error("无法修改权重高于自身的用户"));
        }

        int weightestInRoles = roleMapper.getWeightestInRoles(roleIds);

        if (myWeightest >= weightestInRoles){
            throw new GlobalException(Result.error("无法将权重高于自己的角色分配给用户"));
        }

        //删除原来的角色
        roleMapper.deleteRoleOfUser(userId);

        //添加新角色
        roleIds.forEach(roleId->roleMapper.addRoleOfUser(roleId, userId, IdWorker.getId()));

        //更新权限信息
        authorityAsync.updateAuthorityOfUser(userId);

        return true;
    }
}
