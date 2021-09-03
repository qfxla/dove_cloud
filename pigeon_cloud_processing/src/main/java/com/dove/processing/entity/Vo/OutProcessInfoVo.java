package com.dove.processing.entity.Vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小亮
 * @date 2021/8/25  -  20:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutProcessInfoVo extends Model<OutProcessingVo> {

    private static final long serialVersionUID = 1L;

    //所属加工厂id
    @ApiModelProperty(value = "所属加工厂id")
    @TableField("processing_id")
    @ExcelProperty(value = "加工厂id")
    private Long processingId;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    @ExcelProperty(value = "产品编号")
    private Long typeId;

    //与对应出库单id绑定
    @ApiModelProperty(value = "与对应出库单id绑定")
    @TableField("id")
    @ExcelProperty(value = "绑定出库单id")
    private Long id;

    //加工产品类型名（红烧卤水）
    @ApiModelProperty(value = "加工产品类型名（红烧卤水）")
    @TableField("processing_type")
    @ExcelProperty(value = "产品类型名")
    private String processingType;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("amount")
    @ExcelProperty(value = "数量")
    private Integer amount;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("unit_price")
    @ExcelProperty(value = "单价")
    private Integer unitPrice;

    //金额
    @ApiModelProperty(value = "金额")
    @TableField("price")
    @ExcelProperty(value = "金额")
    private Integer price;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    @ExcelProperty(value = "备注")
    private String remark;
}
