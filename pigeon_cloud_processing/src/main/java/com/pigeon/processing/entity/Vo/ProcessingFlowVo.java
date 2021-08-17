package com.pigeon.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 加工流程表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_processing_flow")
@ApiModel(value = "ProcessingFlow对象", description = "加工流程表")
public class ProcessingFlowVo extends Model<ProcessingFlowVo> {

    private static final long serialVersionUID = 1L;

    //加工流程id
    @ApiModelProperty(value = "加工流程id")
    @TableId(value = "process_id", type = IdType.ASSIGN_UUID)
    private Long processId;

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

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;


}
