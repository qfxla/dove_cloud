package com.dove.breed.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xbx
 */
@Data
public class OperatorDto implements Serializable {

    //基地id
    @ApiModelProperty(value = "基地id")
    private Long baseId;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    //名字
    @ApiModelProperty(value = "名字")
    private String name;

    //手机号
    @ApiModelProperty(value = "手机号")
    private Long phone;

    //性别
    @ApiModelProperty(value = "性别")
    private Boolean sex;
}
