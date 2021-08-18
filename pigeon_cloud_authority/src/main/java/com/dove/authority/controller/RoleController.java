package com.dove.authority.controller;


import com.dove.authority.entity.Role;
import com.dove.authority.entity.bo.RoleBo;
import com.dove.authority.service.RoleService;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
@RestController
@Api(tags = "角色管理相关接口")
@CrossOrigin
@RequestMapping("/authority/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    @ApiOperation("获取企业内的所有角色")
    @PreAuthorize("hasAnyAuthority('basics_authority_select')")
    public Result getRole(){
        return Result.success().data(roleService.getRole());
    }

    @GetMapping("/{page}/{size}")
    @ApiOperation("分页获取企业内的所有角色")
    @PreAuthorize("hasAnyAuthority('basics_authority_select')")
    public Result getRole(@PathVariable(value = "page") Integer page
                            , @PathVariable(value = "size") Integer size){
        return Result.success().data(roleService.getRole(page, size));
    }

    @PostMapping("")
    @ApiOperation("添加角色")
    @PreAuthorize("hasAnyAuthority('basics_authority_insert')")
    public Result createRole(@RequestBody @ApiParam(value = "role")@Validated Role role){
        return roleService.createRole(role)
                ? Result.success("创建成功") : Result.error("创建失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除角色")
    @PreAuthorize("hasAnyAuthority('basics_authority_delete')")
    public Result deleteRole(@PathVariable(value = "id") Long roleId){
        return roleService.deleteRole(roleId)
                ? Result.success("删除成功") : Result.error("删除失败");
    }

    @PutMapping("")
    @ApiOperation("修改角色")
    @PreAuthorize("hasAnyAuthority('basics_authority_update')")
    public Result updateRole(@RequestBody @ApiParam(value = "role")@Validated RoleBo role){
        return roleService.updateRole(role)
                ? Result.success("修改成功") : Result.error("修改失败");
    }

    @PutMapping("/{id}")
    @ApiOperation("修改员工的角色")
    public Result updateRoleOfUser(@RequestBody @ApiParam(value = "roleList")List<Long> roleIds
                                    , @PathVariable(value = "id") Long userId){
        return roleService.updateRoleOfUser(roleIds, userId)
                ? Result.success("修改成功") : Result.error("修改失败");
    }

}

