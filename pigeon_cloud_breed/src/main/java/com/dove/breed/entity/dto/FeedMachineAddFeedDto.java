package com.dove.breed.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: xbx
 */
@Data
@ApiModel(value = "FeedMachineAddFeedDto对象", description = "投喂机添加饲料表")
public class FeedMachineAddFeedDto {

    //饲料名
    @ApiModelProperty(value = "饲料名")
    private String name;

    //饲料规格
    @ApiModelProperty(value = "饲料规格")
    private String type;

    //使用数量
    @ApiModelProperty(value = "使用数量")
    private Integer number;
}