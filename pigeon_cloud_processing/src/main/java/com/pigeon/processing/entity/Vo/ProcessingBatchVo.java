package com.pigeon.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 加工批次表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_processing_batch")
@ApiModel(value = "ProcessingBatch对象", description = "加工批次表")
public class ProcessingBatchVo extends Model<ProcessingBatchVo> {

    private static final long serialVersionUID = 1L;

    //加工批次id
    @ApiModelProperty(value = "加工批次id")
    @TableId(value = "batch_id", type = IdType.ASSIGN_UUID)
    private Long batchId;

    //屠宰批次
    @ApiModelProperty(value = "屠宰批次")
    @TableField("sh_batch")
    private Long shBatch;

    //所属加工厂id
    @ApiModelProperty(value = "所属加工厂id")
    @TableField("processing_id")
    private Long processingId;

    //加工流程名称
    @ApiModelProperty(value = "加工流程名称")
    @TableField("process_name")
    private String processName;

    //加工流程描述
    @ApiModelProperty(value = "加工流程描述")
    @TableField("process_describe")
    private String processDescribe;

    //加工日期
    @ApiModelProperty(value = "加工日期")
    @TableField("processing_time")
    private Date processingTime;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;


}
