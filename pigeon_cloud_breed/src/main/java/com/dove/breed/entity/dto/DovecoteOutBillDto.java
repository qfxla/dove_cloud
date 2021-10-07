package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zcj
 * @creat 2021-08-30-20:07
 */
@Data
public class DovecoteOutBillDto {
    //产量所属基地id
//    @ApiModelProperty(value = "产量所属基地id")
//    @TableField("base_id")
//    private Long baseId;

    //产量所属鸽棚id
    @ApiModelProperty(value = "产量所属鸽棚id")
    @TableField("dovecote_number")
    private String dovecoteNumber;
//
//    //所选类型
//    @ApiModelProperty(value = "订单类型")
//    @TableField("type")
//    private String type;
//
//    //数量
//    @ApiModelProperty(value = "数量")
//    @TableField("amount")
//    private Integer amount;
//
//    //总价
//    @ApiModelProperty(value = "总价")
//    @TableField("total")
//    private Integer total;

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

    //对应的鸽棚出库单信息
    private List<DovecoteOutBaseDto> dovecoteOutBaseDtoList;
}
