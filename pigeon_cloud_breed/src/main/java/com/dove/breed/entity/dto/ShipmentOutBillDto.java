package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

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

    //类型
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //经手人
    @ApiModelProperty(value = "经手人")
    @TableField("handler")
    private String handler;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    //目的地
    @ApiModelProperty(value = "目的地")
    @TableField("destination")
    private String destination;

    //基地出库单下的鸽棚出库单
    private List<DovecoteOutBillDto> dovecoteOutBillDtoList;
}
