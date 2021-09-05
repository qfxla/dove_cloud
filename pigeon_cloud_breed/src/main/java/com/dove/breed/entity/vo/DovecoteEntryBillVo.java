package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-30-19:49
 */
@Data
public class DovecoteEntryBillVo {
    private Long id;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    @TableField("dovecote_number")
    private String dovecoteNumber;

    //所属基地id
    @ApiModelProperty(value = "所属基地id")
    @TableField("base_id")
    private Long baseId;

    //经手人
    @ApiModelProperty(value = "经手人")
    @TableField("handler")
    private String handler;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

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

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
