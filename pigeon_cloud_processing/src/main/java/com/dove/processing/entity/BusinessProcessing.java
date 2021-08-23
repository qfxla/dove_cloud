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
 * 商家表
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_business_processing")
@ApiModel(value = "BusinessProcessing对象", description = "商家表")
public class BusinessProcessing extends Model<BusinessProcessing> {

    private static final long serialVersionUID = 1L;

    //商家id
    @ApiModelProperty(value = "商家id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    //商家名称
    @ApiModelProperty(value = "商家名称")
    @TableField("name")
    private Long name;

    //商家联系方式
    @ApiModelProperty(value = "商家联系方式")
    @TableField("phone")
    private String phone;

    //联系人
    @ApiModelProperty(value = "联系人")
    @TableField("contacts")
    private String contacts;

    //商家类型（供应商或者销售客户）
    @ApiModelProperty(value = "商家类型（供应商或者销售客户）")
    @TableField("type")
    private String type;

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
