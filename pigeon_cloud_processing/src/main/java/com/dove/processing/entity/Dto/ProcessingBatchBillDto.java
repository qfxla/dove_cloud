package com.dove.processing.entity.Dto;

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
 * 加工批次信息表
 * </p>
 *
 * @author WTL
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_processing_batch_bill")
@ApiModel(value = "ProcessingBatchBill对象", description = "加工批次信息表")
public class ProcessingBatchBillDto extends Model<ProcessingBatchBillDto> {

    private static final long serialVersionUID = 1L;

//    //加工批次id
//    @ApiModelProperty(value = "加工批次id")
//    @TableField("batch_id")
//    private Long batchId;

    //所属加工流程id
    @ApiModelProperty(value = "所属加工流程id")
    @TableField("process_id")
    private Long processId;

    //工艺id
    @ApiModelProperty(value = "工艺id")
    @TableField("technology_id")
    private Long technologyId;

//    //某一天加工流程负责人
//    @ApiModelProperty(value = "某一天加工流程负责人")
//    @TableField("process_principal")
//    private String processPrincipal;
//
//    //加工日期
//    @ApiModelProperty(value = "加工日期")
//    @TableField("process_time")
//    private Date processTime;

}
