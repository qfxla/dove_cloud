package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-30-19:53
 */
@Data
public class DovecoteOutBillVo {
    private Long id;

    //产量所属基地id
    @ApiModelProperty(value = "产量所属基地id")
    @TableField("base_id")
    private Long baseId;

    //所属基地名称
    @ApiModelProperty(value = "所属基地名称")
    @TableField("base_name")
    private String baseName;

    //产量所属鸽棚id
    @ApiModelProperty(value = "产量所属鸽棚id")
    @TableField("dovecote_id")
    private Long dovecoteId;

    //产量所属鸽棚id
    @ApiModelProperty(value = "产量所属鸽棚id")
    @TableField("dovecote_number")
    private Long dovecoteNumber;

    //所选类型
    @ApiModelProperty(value = "订单类型")
    @TableField("type")
    private String type;

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

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
