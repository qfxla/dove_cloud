package com.dove.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 加工厂库存表
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_processing_storage")
@ApiModel(value = "ProcessingStorage对象", description = "加工厂库存表")
public class ProcessingStorageVo extends Model<ProcessingStorageVo> {

    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "id")
    @TableId(value = "id")
    private Long id;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    private Long typeId;

    //产品名称
    @ApiModelProperty(value = "产品名称")
    @TableField("type_name")
    private String typeName;

    //规格
    @ApiModelProperty(value = "规格")
    @TableField("specification")
    private String specification;

    //成本单价
    @ApiModelProperty(value = "成本单价")
    @TableField("price")
    private BigDecimal price;

    //库存数量
    @ApiModelProperty(value = "库存数量")
    @TableField("count")
    private Integer count;

    //库存总金额
    @ApiModelProperty(value = "库存总金额")
    @TableField("total")
    private BigDecimal total;

    //预设售价
    @ApiModelProperty(value = "预设售价")
    @TableField("preprice")
    private BigDecimal preprice;

    //参考金额
    @ApiModelProperty(value = "参考金额")
    @TableField("refer_price")
    private BigDecimal referPrice;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


}
