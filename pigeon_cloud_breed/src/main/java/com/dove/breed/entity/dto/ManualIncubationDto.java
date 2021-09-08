package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-09-05-15:15
 */
@Data
public class ManualIncubationDto {

    @ApiModelProperty(value = "基地id")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "类型")
    private int type;

    @ApiModelProperty(value = "上午数量")
    private Integer amNumber;

    @ApiModelProperty(value = "下午数量")
    private Integer pmNumber;
}
