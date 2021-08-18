package com.dove.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dove.authority.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.authority.entity.vo.PermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
public interface PermissionService extends IService<Permission> {

    public List<PermissionVo> getPermission();

    public List<PermissionVo> getPermissionOfRole(Long roleId);

}
