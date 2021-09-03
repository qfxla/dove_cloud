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
 * 加工产品类型表
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_processing_type")
@ApiModel(value = "ProcessingType对象", description = "加工产品类型表")
public class ProcessingType extends Model<ProcessingType> {

    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "id")
    @TableId(value = "type_id", type = IdType.ASSIGN_ID)
    private Long typeId;

    //所属加工厂id
    @ApiModelProperty(value = "所属加工厂id")
    @TableField("processing_id")
    private Long processingId;

    //所属加工流程id
    @ApiModelProperty(value = "所属加工流程id")
    @TableField("process_id")
    private Long processId;

    //加工产品类型名（红烧 卤水）
    @ApiModelProperty(value = "加工产品类型名（红烧 卤水）")
    @TableField("processing_type")
    private String processingType;

    //规格
    @ApiModelProperty(value = "规格")
    @TableField("specifications")
    private String specifications;

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
