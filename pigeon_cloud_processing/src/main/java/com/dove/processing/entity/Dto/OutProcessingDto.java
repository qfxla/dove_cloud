package com.dove.processing.entity.Dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 加工厂出库表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutProcessingDto extends Model<OutProcessingDto> {

    private static final long serialVersionUID = 1L;

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
    @ApiModelProperty(value = "商品备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "绑定表的信息")
    List<OutProcessingBillDto> outProcessingBillDtos;

}
