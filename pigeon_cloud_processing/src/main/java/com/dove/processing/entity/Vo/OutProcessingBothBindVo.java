package com.dove.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 加工厂出库表
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_out_processing")
@ApiModel(value = "OutProcessing对象", description = "加工厂出库表")
public class OutProcessingBothBindVo extends Model<OutProcessingBothBindVo> {

    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "out_id")
    @TableId(value = "out_id", type=IdType.INPUT)
    private Long outId;

    //所属加工厂id
    @ApiModelProperty(value = "所属加工厂id")
    @TableField("processing_id")
    private Long processingId;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    private Long typeId;

    //加工产品类型名（红烧卤水）
    @ApiModelProperty(value = "加工产品类型名（红烧卤水）")
    @TableField("processing_type")
    private String processingType;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("amount")
    private Integer amount;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("unit_price")
    private Integer unitPrice;

    //金额
    @ApiModelProperty(value = "金额")
    @TableField("price")
    private Integer price;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    //id
    @ApiModelProperty(value = "id")
    @TableId(value = "id")
    private Long id;

    //经手人
    @ApiModelProperty(value = "经手人")
    @TableField("handler")
    private String handler;

    //提货商id(商家id)
    @ApiModelProperty(value = "提货商id(商家id)")
    @TableField("consignee")
    private String consignee;

    //商家名称
    @ApiModelProperty(value = "商家名称")
    @TableField("name")
    private String name;

    //总数量
    @ApiModelProperty(value = "总数量")
    @TableField("total_amount")
    private Integer totalAmount;

    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total")
    private Integer total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("total_remark")
    private String totalRemark;

    //出库时间
    @ApiModelProperty(value = "出库时间")
    @TableField("out_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date outTime;


}
