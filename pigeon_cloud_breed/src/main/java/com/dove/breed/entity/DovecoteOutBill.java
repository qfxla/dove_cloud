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
 * 鸽棚出仓单
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dovecote_out_bill")
@ApiModel(value = "DovecoteOutBill对象", description = "鸽棚出仓单")
public class DovecoteOutBill extends Model<DovecoteOutBill> {

    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //对应基地出库单批次号
    @ApiModelProperty(value = "对应基地出库单批次号")
    @TableField(value = "farm_batch")
    private String farmBatch;

    //对应基地出库单id
    @ApiModelProperty(value = "对应基地出库单id")
    @TableField(value = "shipment_out_bill")
    private Long shipmentOutBill;

    //产量所属基地id
    @ApiModelProperty(value = "产量所属基地id")
    @TableField("base_id")
    private Long baseId;


    //产量所属鸽棚编号
    @ApiModelProperty(value = "产量所属鸽棚id")
    @TableField("dovecote_number")
    private String dovecoteNumber;

    //所选类型
    @ApiModelProperty(value = "订单类型")
    @TableField("type")
    private String type;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("amount")
    private Integer amount;

    //总价
    @ApiModelProperty(value = "总价")
    @TableField("total")
    private Integer total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    //所属鸽棚负责人
    @ApiModelProperty(value = "所属鸽棚负责人")
    @TableField("dovecote_director")
    private String dovecoteDirector;

    //员工姓名(类型为鸽粪时需要)
    @ApiModelProperty(value = "员工姓名(类型为鸽粪时需要)")
    @TableField("employee_name")
    private String employeeName;

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
