package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-20-21:00
 */
@Data
public class ShipmentEntryTypeDto {
    private static final long serialVersionUID = 1L;

    //基地id
    @ApiModelProperty(value = "基地id")
    @TableField(value = "base_id")
    private Long baseId;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField(value = "product_number")
    private Integer productNumber;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    //类型
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;


    //规格
    @ApiModelProperty(value = "规格")
    @TableField("specifications")
    private String specifications;

}
