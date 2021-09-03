package com.dove.processing.entity.Dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 加工厂入库信息表
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_in_processing_info")
@ApiModel(value = "InProcessingInfo对象", description = "加工厂入库信息表")
public class InProcessingInfoDto extends Model<InProcessingInfoDto> {

    private static final long serialVersionUID = 1L;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    private Long typeId;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("material_name")
    private String materialName;

    //规格
    @ApiModelProperty(value = "规格")
    @TableField("specification")
    private String specification;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("price")
    private BigDecimal price;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("count")
    private Integer count;

    //总价
    @ApiModelProperty(value = "总价")
    @TableField("total")
    private BigDecimal total;

    //备注
    @ApiModelProperty(value = "商品备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "入库单信息表")
    List<InProcessingBillDto> inProcessingBills;

}
