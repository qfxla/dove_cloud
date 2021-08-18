package com.dove.authority.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dove.authority.Async.AuthorityAsync;
import com.dove.authority.entity.Package;
import com.dove.authority.entity.Permission;
import com.dove.authority.entity.vo.PermissionVo;
import com.dove.authority.mapper.PermissionMapper;
import com.dove.authority.mapper.RoleMapper;
import com.dove.authority.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import com.dove.entity.UserDetailsImpl;
import com.dove.util.SecurityContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private AuthorityAsync authorityAsync;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<PermissionVo> getPermission() {
        UserDetailsImpl userDetails = SecurityContextUtil.getUserDetails();

        List<Long> roleIds = roleMapper.findRoleIdsOfUser(userDetails.getId());

        Future<List<PermissionVo>> allPermission = authorityAsync.getAllPermission(roleIds);

        while (!allPermission.isDone()){
            Thread.yield();
        }

        List<PermissionVo> result;

        try {
            result = allPermission.get();
        } catch (Exception e) {
            throw new GlobalException(Result.error("获取失败"));
        }

        return result;
    }

    @Override
    public List<PermissionVo> getPermissionOfRole(Long roleId) {
        Package<List<PermissionVo>> listPackage = new Package<>();

        authorityAsync.getAllPermission(roleId, listPackage);

        synchronized (listPackage){
            while (listPackage.getValue() == null){
                try {
                    listPackage.wait();
                } catch (InterruptedException e) {
                    throw new GlobalException(Result.error("获取失败"));
                }
            }
        }

        return listPackage.getValue();
    }
}
