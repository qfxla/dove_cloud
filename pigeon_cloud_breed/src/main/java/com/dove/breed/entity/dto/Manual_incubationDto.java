package com.dove.breed.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-09-05-15:15
 */
@Data
public class Manual_incubationDto {
    @ApiModelProperty(value = "基地id")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "饲养员名字")
    private String breederName;

    @ApiModelProperty(value = "类型")
    private int type;

    @ApiModelProperty(value = "上午数量")
    private Integer amNumber;

    @ApiModelProperty(value = "下午数量")
    private Integer pmNumber;
}
