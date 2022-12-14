package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-30-20:04
 */
@Data
public class DovecoteEntryBillDto {

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

    //入鸽棚时间
    @ApiModelProperty(value = "进鸽棚时间")
    @TableField("in_time")
    private Date inTime;

    //总数量
    @ApiModelProperty(value = "总数量")
    @TableField("amount")
    private Integer amount;

    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total")
    private Integer total;

    //类型
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
}
