package com.dove.processing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 加工厂入库信息表
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_in_processing_info")
@ApiModel(value = "InProcessingInfo对象", description = "加工厂入库信息表")
public class InProcessingInfo extends Model<InProcessingInfo> {

    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    //与对应入库单绑定
    @ApiModelProperty(value = "与对应入库单绑定")
    @TableField("in_id")
    private Long inId;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    private Long typeId;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("material_name")
    private String materialName;

    //规格
    @ApiModelProperty(value = "规格")
    @TableField("specification")
    private String specification;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("price")
    private BigDecimal price;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("count")
    private Integer count;

    //总价
    @ApiModelProperty(value = "总价")
    @TableField("total")
    private BigDecimal total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

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
