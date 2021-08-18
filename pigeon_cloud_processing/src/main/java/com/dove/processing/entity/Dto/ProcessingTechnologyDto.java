package com.dove.processing.entity.Dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 加工工艺表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_processing_technology")
@ApiModel(value = "ProcessingTechnology对象", description = "加工工艺表")
public class ProcessingTechnologyDto extends Model<ProcessingTechnologyDto> {

    private static final long serialVersionUID = 1L;

    //工艺id
    @ApiModelProperty(value = "工艺id")
    @TableId(value = "technology_id", type = IdType.ASSIGN_UUID)
    private Long technologyId;

    //工艺名称
    @ApiModelProperty(value = "工艺名称")
    @TableField("technology_name")
    private String technologyName;

    //工艺描述
    @ApiModelProperty(value = "工艺描述")
    @TableField("technology_describe")
    private String technologyDescribe;

    //所属加工流程id
    @ApiModelProperty(value = "所属加工流程id")
    @TableField("process_id")
    private Long processId;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;




}
