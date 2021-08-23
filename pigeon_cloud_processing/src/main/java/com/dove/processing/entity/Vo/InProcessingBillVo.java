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
 * 加工厂入库单表
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_in_processing_bill")
@ApiModel(value = "InProcessingBill对象", description = "加工厂入库单表")
public class InProcessingBillVo extends Model<InProcessingBillVo> {

    private static final long serialVersionUID = 1L;

    //入库id
    @ApiModelProperty(value = "入库id")
    @TableId(value = "in_id")
    private Long inId;

    //来源（商家id）
    @ApiModelProperty(value = "来源（商家id）")
    @TableField("source")
    private Long source;

    //入库仓库
    @ApiModelProperty(value = "入库仓库")
    @TableField("in_storage")
    private String inStorage;

    //总数量
    @ApiModelProperty(value = "总数量")
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

    //入库时间
    @ApiModelProperty(value = "入库时间")
    @TableField("enter_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enterTime;

}
