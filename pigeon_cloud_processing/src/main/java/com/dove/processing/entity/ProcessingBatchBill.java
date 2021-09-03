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
public class ProcessingBatchBill extends Model<ProcessingBatchBill> {

    private static final long serialVersionUID = 1L;

    //主键id
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    //加工批次id
    @ApiModelProperty(value = "加工批次id")
    @TableField("batch_id")
    private Long batchId;

    //所属加工流程id
    @ApiModelProperty(value = "所属加工流程id")
    @TableField("process_id")
    private Long processId;

    //工艺id
    @ApiModelProperty(value = "工艺id")
    @TableField("technology_id")
    private Long technologyId;

    //某一天加工流程负责人
    @ApiModelProperty(value = "某一天加工流程负责人")
    @TableField("process_principal")
    private String processPrincipal;

    //加工日期
    @ApiModelProperty(value = "加工日期")
    @TableField("process_time")
    private Date processTime;

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
