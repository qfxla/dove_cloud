package com.dove.processing.entity.Dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class InProcessingBillDto extends Model<InProcessingBillDto> {

    private static final long serialVersionUID = 1L;

    //来源（商家id）
    @ApiModelProperty(value = "来源（商家id）")
    @TableField("source")
    private Long source;

    //加工批次id
    @ApiModelProperty(value = "加工批次id")
    @TableId(value = "batch_id")
    private Long batchId;

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
    @TableField("total_money")
    private Integer totalMoney;

    //备注
    @ApiModelProperty(value = "入库信息备注")
    @TableField("total_remark")
    private String totalRemark;

    //入库时间
    @ApiModelProperty(value = "入库时间")
    @TableField("enter_time")
    private Date enterTime;

}
