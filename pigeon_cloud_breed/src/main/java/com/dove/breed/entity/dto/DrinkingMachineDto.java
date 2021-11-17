package com.dove.breed.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: xbx
 */
@Data
public class DrinkingMachineDto {

    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "饮水机编号")
    private String machineNumber;

    @ApiModelProperty(value = "峰值扬程")
    private String peakHead;

    @ApiModelProperty(value = "峰值吸程")
    private String peakSuction;

    @ApiModelProperty(value = "峰值流量")
    private String peakFlow;

    @ApiModelProperty(value = "转速")
    private String rev;
}
