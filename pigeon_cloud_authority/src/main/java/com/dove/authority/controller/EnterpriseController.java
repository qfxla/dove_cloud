package com.dove.authority.controller;


import com.dove.authority.entity.Enterprise;
import com.dove.authority.service.EnterpriseService;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author run
 * @since 2021-03-21
 */
@Api(tags = "企业管理相关接口")
@CrossOrigin
@RestController
@RequestMapping("/authority/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping("")
    @ApiOperation("创建企业")
    public Result createEnterprise(@RequestBody @ApiParam(value = "enterprise")@Validated Enterprise enterprise){
        return enterpriseService.createEnterprise(enterprise)
                ? Result.success("创建成功") : Result.error("创建失败");
    }

    @GetMapping("")
    @ApiOperation("获取企业信息")
    @PreAuthorize("hasAnyAuthority('basics_enterprise_select')")
    public Result getEnterpriseInfo(){
        return Result.success().data(enterpriseService.getEnterpriseInfo());
    }

    @PutMapping("")
    @ApiOperation("修改企业信息")
    @PreAuthorize("hasAnyAuthority('basics_enterprise_update')")
    public Result updateEnterprise(@RequestBody @ApiParam(value = "enterprise")@Validated Enterprise enterprise){
        return enterpriseService.updateEnterprise(enterprise)
                ? Result.success("修改成功") : Result.error("修改失败");
    }

    @GetMapping("/enterprise")
    @ApiOperation("获取企业的员工信息")
    @PreAuthorize("hasAnyAuthority('basics_staff_select')")
    public Result getStaffInfo(){
        return Result.success().data(enterpriseService.getStaffInfo());
    }

    @GetMapping("/enterprise/{page}/{size}")
    @ApiOperation("分页获取企业的员工信息")
    @PreAuthorize("hasAnyAuthority('basics_staff_select')")
    public Result getStaffInfo(@PathVariable(value = "page") Integer page
                                , @PathVariable(value = "size") Integer size){
        return Result.success().data(enterpriseService.getStaffInfo(page, size));
    }

    @PutMapping("shift/{id}")
    @ApiOperation("将用户移出基地")
    @PreAuthorize("hasAnyAuthority('basics_staff_delete')")
    public Result shiftOutUser(@PathVariable(value = "id") Long userId){
        return enterpriseService.shiftOutUser(userId)
                ? Result.success("移出成功") : Result.error("移出失败");
    }

    @GetMapping("/inviteCode")
    @ApiOperation("生成基地邀请码(有效期一小时)")
    public Result generateInviteCode(){
        return Result.success().data(enterpriseService.generateInviteCode());
    }
}

