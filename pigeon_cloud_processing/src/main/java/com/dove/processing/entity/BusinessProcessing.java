package com.dove.processing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商家表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
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
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
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

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    //逻辑删除
    @ApiModelProperty(value = "逻辑删除")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    //乐观锁(版本号)
    @ApiModelProperty(value = "乐观锁(版本号)")
    @TableField("version")
    @Version
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
