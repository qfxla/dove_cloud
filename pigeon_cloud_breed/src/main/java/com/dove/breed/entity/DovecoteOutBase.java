package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 鸽棚出仓信息表
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dovecote_out_base")
@ApiModel(value = "DovecoteOutBase对象", description = "鸽棚出仓信息表")
public class DovecoteOutBase extends Model<DovecoteOutBase> {

    private static final long serialVersionUID = 1L;

    //产量单id
    @ApiModelProperty(value = "产量单id")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //对应的出仓单id
    @ApiModelProperty(value = "对应的出仓单id")
    @TableField("dovecote_out_bill")
    private Long dovecoteOutBill;

    //类型
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    private Long typeId;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("type_name")
    private String typeName;


    //数量
    @ApiModelProperty(value = "数量")
    @TableField("amount")
    private Integer amount;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("unit_price")
    private Integer unitPrice;

    //总价
    @ApiModelProperty(value = "总价")
    @TableField("total")
    private Integer total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

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
    private Boolean deleted;

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
