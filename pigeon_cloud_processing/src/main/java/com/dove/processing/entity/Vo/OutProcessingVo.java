package com.dove.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 加工厂出库表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_out_processing")
@ApiModel(value = "OutProcessing对象", description = "加工厂出库表")
public class OutProcessingVo extends Model<OutProcessingVo> {

    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "id")
    @TableId(value = "out_id", type = IdType.ASSIGN_UUID)
    private Long outId;

    //所属加工厂id
    @ApiModelProperty(value = "所属加工厂id")
    @TableField("processing_id")
    private Long processingId;

    //与对应出库单id绑定
    @ApiModelProperty(value = "与对应出库单id绑定")
    @TableField("id")
    private Long id;

    //加工产品类型名（红烧卤水）
    @ApiModelProperty(value = "加工产品类型名（红烧卤水）")
    @TableField("processing_type")
    private String processingType;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("amout")
    private Integer amout;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("unit_price")
    private Integer unitPrice;

    //金额
    @ApiModelProperty(value = "金额")
    @TableField("price")
    private Integer price;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;



}
