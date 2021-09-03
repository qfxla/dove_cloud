package com.dove.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 加工流程表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingFlowBindBatchBillVo extends Model<ProcessingFlowBindBatchBillVo> {

    private static final long serialVersionUID = 1L;

    //加工流程id
    @ApiModelProperty(value = "加工流程id")
    @TableId(value = "process_id")
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

    //加工批次id
    @ApiModelProperty(value = "加工批次id")
    @TableField("batch_id")
    private Long batchId;

    //某一天加工流程负责人
    @ApiModelProperty(value = "某一天加工流程负责人")
    @TableField("process_principal")
    private String processPrincipal;

    //加工日期
    @ApiModelProperty(value = "加工日期")
    @TableField("process_time")
    private Date processTime;

}
