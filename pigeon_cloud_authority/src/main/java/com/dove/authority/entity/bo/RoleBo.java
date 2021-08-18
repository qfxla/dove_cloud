package com.dove.authority.entity.bo;

import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author run
 * @since 2021/3/19 19:38
 */
public class RoleBo {

    @ApiModelProperty(value = "角色id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    @NotNull
    private String name;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "权限id列表")
    private List<Long> permissionIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null){
            throw new GlobalException(Result.error("角色id不能为空"));
        }
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

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
