package com.dove.authority.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author run
 * @since 2021/3/18 23:30
 */
public class PermissionVo {

    @ApiModelProperty(value = "权限id")
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "访问地址")
    private String path;

    @ApiModelProperty(value = "父权限id")
    private Long parentId;

    @ApiModelProperty(value = "子权限列表")
    private List<PermissionVo> childPermission;


    public List<PermissionVo> getChildPermission() {
        return childPermission;
    }

    public void setChildPermission(List<PermissionVo> childPermission) {
        this.childPermission = childPermission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
}
