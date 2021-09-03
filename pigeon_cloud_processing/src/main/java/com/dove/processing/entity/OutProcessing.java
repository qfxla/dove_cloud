package com.dove.processing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class OutProcessing extends Model<OutProcessing> {

    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "id")
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

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    //逻辑删除
    @ApiModelProperty(value = "逻辑删除")
    @TableField(value = "is_deleted",fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    //乐观锁(版本号)
    @ApiModelProperty(value = "乐观锁(版本号)")
    @TableField(value = "version",fill = FieldFill.INSERT)
    @Version
    private Integer version;

}
