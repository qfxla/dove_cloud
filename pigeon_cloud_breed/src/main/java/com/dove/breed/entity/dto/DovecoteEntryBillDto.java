package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-30-20:04
 */
@Data
public class DovecoteEntryBillDto {
    //领取部门(鸽棚id)
    @ApiModelProperty(value = "领取部门(鸽棚id)")
    @TableField("dovecote_id")
    private Long dovecoteId;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    @TableField("dovecote_number")
    private String dovecoteNumber;

    //所属基地id
    @ApiModelProperty(value = "所属基地id")
    @TableField("base_id")
    private Long baseId;

    //所属基地名称
    @ApiModelProperty(value = "所属基地名称")
    @TableField("base_name")
    private String baseName;

    //经手人
    @ApiModelProperty(value = "经手人")
    @TableField("handler")
    private String handler;

    //总金额
    @ApiModelProperty(value = "总金额")
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
}
