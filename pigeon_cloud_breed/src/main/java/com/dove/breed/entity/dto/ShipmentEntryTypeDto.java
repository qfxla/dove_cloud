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

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    //规格
    @ApiModelProperty(value = "规格")
    @TableField("specifications")
    private String specifications;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;
}
