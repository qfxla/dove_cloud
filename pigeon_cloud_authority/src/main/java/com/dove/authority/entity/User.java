package com.dove.authority.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author WTL
 * @since 2021-09-05
 */
@TableName("t_authority_user")
@ApiModel(value = "AuthorityUser对象", description = "用户信息表")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    //用户id
    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //用户唯一标识（openid)
    @ApiModelProperty(value = "用户唯一标识（openid)")
    @TableField("cloud_id")
    private String cloudId;

    //手机号码
    @ApiModelProperty(value = "手机号码")
    @TableField("phone")
    private String phone;

    //登录密码
    @ApiModelProperty(value = "登录密码")
    @TableField("password")
    private String password;

    //web端登录名称
    @ApiModelProperty(value = "web端登录名称")
    @TableField("username")
    private String username;

    //性别：男1，女2
    @ApiModelProperty(value = "性别：男1，女2")
    @TableField("sex")
    private Integer sex;

    //头像
    @ApiModelProperty(value = "头像")
    @TableField("profile_photo")
    private String profilePhoto;

    //小程序用户名
    @ApiModelProperty(value = "小程序用户名")
    @TableField("account")
    private String account;

    //出生日期
    @ApiModelProperty(value = "出生日期")
    @TableField("birthday")
    private Date birthday;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("enterprise_id")
    private Long enterpriseId;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    //逻辑删除
    @ApiModelProperty(value = "逻辑删除")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    //乐观锁(版本号)
    @ApiModelProperty(value = "乐观锁(版本号)")
    @TableField("version")
    @Version
    private Integer version;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
