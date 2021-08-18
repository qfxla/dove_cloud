package com.dove.authority.entity.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author run
 * @since 2021/3/18 23:30
 */
public class RoleVo {

    @ApiModelProperty(value = "角色id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "角色权重（范围1-99）")
    private Integer weight;

    @ApiModelProperty(value = "是否可用（0否，1是）")
    private Integer usable;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public Integer getUsable() {
        return usable;
    }

    public void setUsable(Integer usable) {
        this.usable = usable;
    }
}
