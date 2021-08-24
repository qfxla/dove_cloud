package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-20-20:06
 */
@Data
public class ShipmentOutBaseDto {
    private static final long serialVersionUID = 1L;

    //与基地出库单批次对应
    @ApiModelProperty(value = "与基地出库单批次对应")
    @TableField("farm_batch")
    private Long farmBatch;

    //类型(五两鸽..灰鸽)
    @ApiModelProperty(value = "类型(五两鸽..灰鸽)")
    @TableField("type")
    private String type;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    private Long typeId;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("type_name")
    private String typeName;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("unit_price")
    private Integer unitPrice;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("amount")
    private Integer amount;

    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total")
    private Integer total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;
}
