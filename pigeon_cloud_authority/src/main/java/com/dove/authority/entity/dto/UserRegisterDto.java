package com.dove.authority.entity.dto;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author run
 * @since 2021/3/19 21:14
 */
public class UserRegisterDto {

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("密码")
    @NotNull
    private String password;

    @ApiModelProperty("短信验证码")
    @NotNull
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!Validator.isMobile(phone)){
            throw new GlobalException(Result.error("手机号不合法"));
        }
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (StrUtil.isEmpty(password)){
            throw new GlobalException(Result.error("密码不能为空"));
        }
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (StrUtil.isEmpty(code) || code.length() != 6){
            throw new GlobalException(Result.error("短信验证码不合法"));
        }
        this.code = code;
    }
}
