package com.dove.processing.entity.Vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 小亮
 * @date 2021/8/22  -  23:44
 */
public class BillBindInfoVo {

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    @ExcelProperty(value = "产品编号")
    private Long typeId;

    //加工产品类型名（红烧卤水）
    @ApiModelProperty(value = "加工产品类型名（红烧卤水）")
    @TableField("processing_type")
    @ExcelProperty(value = "加工产品类型名（红烧卤水）")
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

    //经手人
    @ApiModelProperty(value = "经手人")
    @TableField("handler")
    @ExcelProperty(value = "经手人")
    private String handler;

    //商家名称
    @ApiModelProperty(value = "商家名称")
    @TableField("name")
    @ExcelProperty(value = "商家名称")
    private String name;

    //总数量
    @ApiModelProperty(value = "总数量")
    @TableField("total_amount")
    @ExcelProperty(value = "总数量")
    private Integer totalAmount;

    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total")
    @ExcelProperty(value = "总金额")
    private Integer total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    @ExcelProperty(value = "备注")
    private String remark;

    //出库时间
    @ApiModelProperty(value = "出库时间")
    @TableField("out_time")
    @ExcelProperty(value = "出库时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime outTime;



}
