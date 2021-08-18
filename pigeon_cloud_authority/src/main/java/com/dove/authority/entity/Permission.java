package com.dove.authority.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
@TableName("t_authority_permission")
@ApiModel(value="Permission对象", description="")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限id")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "访问地址")
    private String path;

    @ApiModelProperty(value = "父权限id")
    private Long parentId;

    @ApiModelProperty(value = "创建时间" ,hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间" ,hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "逻辑删除（0否，1是）" ,hidden = true)
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    @ApiModelProperty(value = "乐观锁（版本号）" ,hidden = true)
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    public Permission(String name, String remarks, Long parentId) {
        this.name = name;
        this.remarks = remarks;
        this.parentId = parentId;
    }

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    @Override
    public String toString() {
        return "Permission{" +
        "id=" + id +
        ", name=" + name +
        ", remarks=" + remarks +
        ", path=" + path +
        ", parentId=" + parentId +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", isDeleted=" + isDeleted +
        ", version=" + version +
        "}";
    }
}
