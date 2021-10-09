package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-20-20:36
 */
@Data
public class ShipmentEntryBaseDto {
    private static final long serialVersionUID = 1L;

    //与基地入库单id绑定
    @ApiModelProperty(value = "与基地入库单id绑定")
    @TableField("shipment_entry_bill")
    private Long shipmentEntryBill;

    //类型
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    private Integer typeId;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("type_name")
    private String typeName;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("unit_price")
    private Integer unitPrice;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("amount")
    private Integer amount;

    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total")
    private Integer total;

    //供应商id
    @ApiModelProperty(value = "供应商id")
    @TableField("supplier_id")
    private Long supplierId;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

}
