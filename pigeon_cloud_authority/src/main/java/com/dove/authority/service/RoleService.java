package com.dove.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.authority.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.authority.entity.bo.RoleBo;
import com.dove.authority.entity.vo.RoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
public interface RoleService extends IService<Role> {

    public boolean updateRole(RoleBo role);

    public boolean createRole(Role role);

    public boolean deleteRole(Long roleId);

    public IPage<RoleVo> getRole(Integer page, Integer size);

    public List<RoleVo> getRole();

    public boolean updateRoleOfUser(List<Long> roleIds,Long userId);

}
