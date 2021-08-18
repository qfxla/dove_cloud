package com.dove.authority.controller;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dove.authority.entity.Permission;
import com.dove.authority.mapper.PermissionMapper;
import com.dove.authority.service.PermissionService;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
@RestController
@Api(tags = "权限管理相关接口")
@CrossOrigin
@RequestMapping("/authority/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @GetMapping("")
    @ApiOperation("查询所有权限")
    @PreAuthorize("hasAnyAuthority('basics_authority_select')")
    public Result getPermission(){
        return Result.success().data(permissionService.getPermission());
    }

    @GetMapping("/rolePermission/{id}")
    @ApiOperation("获取角色的权限信息")
    @PreAuthorize("hasAnyAuthority('basics_authority_select')")
    public Result getPermissionOfRole(@PathVariable(value = "id") Long roleId){
        return Result.success().data(permissionService.getPermissionOfRole(roleId));
    }

}

