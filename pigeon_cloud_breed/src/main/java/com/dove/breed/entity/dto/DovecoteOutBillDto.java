package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-30-20:07
 */
@Data
public class DovecoteOutBillDto {
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

    //所属鸽棚负责人
    @ApiModelProperty(value = "所属鸽棚负责人")
    @TableField("dovecote_director")
    private String dovecoteDirector;

    //员工姓名(类型为鸽粪时需要)
    @ApiModelProperty(value = "员工姓名(类型为鸽粪时需要)")
    @TableField("employee_name")
    private String employeeName;
}
