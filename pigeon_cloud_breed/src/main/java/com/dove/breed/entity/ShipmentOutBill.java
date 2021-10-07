package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基地出库单
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shipment_out_bill")
@ApiModel(value = "ShipmentOutBill对象", description = "基地出库单")
public class ShipmentOutBill extends Model<ShipmentOutBill> {

    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //养殖批次
    @ApiModelProperty(value = "养殖批次")
    @TableId(value = "farm_batch")
    private String farmBatch;

    //基地id
    @ApiModelProperty(value = "基地id")
    @TableField("base_id")
    private Long baseId;

    //基地名称
    @ApiModelProperty(value = "基地名称")
    @TableField("base_name")
    private String baseName;

    //类型
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //经手人
    @ApiModelProperty(value = "经手人")
    @TableField("handler")
    private String handler;

    //总数量
    @ApiModelProperty(value = "总数量")
    @TableField("amount")
    private Integer amount;

    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total")
    private Integer total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    //目的地
    @ApiModelProperty(value = "目的地")
    @TableField("destination")
    private String destination;

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
        return this.farmBatch;
    }

}
