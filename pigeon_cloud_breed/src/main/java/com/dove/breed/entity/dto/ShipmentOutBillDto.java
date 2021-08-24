package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-20-19:47
 */
@Data
public class ShipmentOutBillDto {
    private static final long serialVersionUID = 1L;

    //基地id
    @ApiModelProperty(value = "基地id")
    @TableField("base_id")
    private Long baseId;

    //基地名称
    @ApiModelProperty(value = "基地名称")
    @TableField("base_name")
    private String baseName;

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
}
