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
public class InProcessingBill extends Model<InProcessingBill> {

    private static final long serialVersionUID = 1L;

    //入库id
    @ApiModelProperty(value = "入库id")
    @TableId(value = "in_id", type = IdType.ASSIGN_ID)
    private Long inId;

    //来源（商家id）
    @ApiModelProperty(value = "来源（商家id）")
    @TableField("source")
    private Long source;

    //加工批次id
    @ApiModelProperty(value = "加工批次id")
    @TableField(value = "batch_id")
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
    @ApiModelProperty(value = "备注")
    @TableField("total_remark")
    private String totalRemark;

    //入库时间
    @ApiModelProperty(value = "入库时间")
    @TableField("enter_time")
    private Date enterTime;

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
