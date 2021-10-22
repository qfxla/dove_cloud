package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 清粪信息表
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
@Data
@TableName("t_clear_soil")
@ApiModel(value="ClearSoil对象", description="清粪信息表")
public class ClearSoil implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    @ApiModelProperty(value = "清理时间")
    private Date clearTime;

    @ApiModelProperty(value = "重量")
    private Integer weight;

    @ApiModelProperty(value = "重量的单位")
    private String type;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "企业id")
    private Long guige;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    //逻辑删除(0否,1是)
    @ApiModelProperty(value = "逻辑删除(0否,1是)")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "版本号")
    @Version
    private Integer version;
}
