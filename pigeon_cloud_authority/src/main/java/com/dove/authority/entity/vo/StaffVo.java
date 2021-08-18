package com.dove.authority.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @author run
 * @since 2021/3/22 8:50
 */
public class StaffVo {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "性别：男1，女2")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String profilePhoto;

    @ApiModelProperty(value = "角色列表")
    private List<RoleVo> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public List<RoleVo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVo> roles) {
        this.roles = roles;
    }
}
