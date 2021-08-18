package com.dove.authority.controller;

import com.dove.authority.entity.User;
import com.dove.authority.entity.dto.UserLoginPasswordDto;
import com.dove.authority.entity.dto.UserLoginPhoneDto;
import com.dove.authority.entity.dto.UserRegisterDto;
import com.dove.authority.entity.vo.UserVo;
import com.dove.authority.service.PermissionService;
import com.dove.authority.service.UserService;
import com.dove.authority.utils.IpAddressUtil;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
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
@Api(tags = "用户相关接口")
@CrossOrigin
@RequestMapping("/authority/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody @ApiParam(value = "user")@Validated UserRegisterDto userDto){
        return userService.register(userDto, request, response)
                ? Result.success("注册成功") : Result.error("注册失败");
    }

    @PostMapping("/login")
    @ApiOperation("用户登录（手机号验证码登录）")
    public Result login(@RequestBody @ApiParam(value = "user")@Validated UserLoginPhoneDto userDto){
        return userService.login(userDto, request, response)
                ? Result.success("登录成功") : Result.error("登录失败");
    }

    @PutMapping("/login")
    @ApiOperation("用户登录（手机号密码登录）")
    public Result login(@RequestBody @ApiParam(value = "user")@Validated UserLoginPasswordDto userDto){
        return userService.login(userDto, request, response)
                ? Result.success("登录成功") : Result.error("登录失败");
    }

    @PutMapping("/retrieve")
    @ApiOperation("找回密码")
    public Result retrieve(@RequestBody @ApiParam(value = "user") @Validated UserRegisterDto userDto){
        return userService.retrieve(userDto, request, response)
                ? Result.success("密码修改成功") : Result.error("找回失败");
    }

    @PutMapping("")
    @ApiOperation("修改用户信息")
    public Result updateUser(@RequestBody @ApiParam(value = "user")@Validated User user){
        return userService.updateUser(user)
                ? Result.success("修改成功") : Result.error("修改失败");
    }

    @GetMapping("")
    @ApiOperation("获取用户信息")
    public Result getUserInfo(){
        return Result.success().data(userService.getUserInfo());
    }

    @GetMapping("/permission")
    @ApiOperation("获取用户的所有权限")
    public Result getUserPermission(){
        return Result.success().data(permissionService.getPermission());
    }

    @PutMapping("/join/{code}")
    @ApiOperation("通过邀请码加入基地")
    public Result joinEnterprise(@PathVariable(value = "code") String inviteCode){
        return userService.joinEnterprise(inviteCode)
                ? Result.success("加入成功") : Result.error("加入失败");
    }
}

