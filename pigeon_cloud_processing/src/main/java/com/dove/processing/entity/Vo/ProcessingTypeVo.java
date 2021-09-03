package com.dove.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 加工产品类型表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingTypeVo extends Model<ProcessingTypeVo> {

    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "id")
    @TableId(value = "type_id")
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

}
