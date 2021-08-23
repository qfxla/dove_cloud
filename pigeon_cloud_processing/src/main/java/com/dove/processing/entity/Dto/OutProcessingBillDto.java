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

import java.util.Date;

/**
 * <p>
 * 加工厂出库单表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutProcessingBillDto extends Model<OutProcessingBillDto> {

    private static final long serialVersionUID = 1L;


    //与对应出库单id绑定
    @ApiModelProperty(value = "与对应出库单id绑定")
    @TableField("out_id")
    private Long outId;

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
    @TableField("remark")
    private String remark;

    //出库时间
    @ApiModelProperty(value = "出库时间")
    @TableField("out_time")
    private Date outTime;
}
