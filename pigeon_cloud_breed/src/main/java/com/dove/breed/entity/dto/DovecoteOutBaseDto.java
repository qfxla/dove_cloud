package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-30-20:06
 */
@Data
public class DovecoteOutBaseDto {
    //对应的出仓单id
    @ApiModelProperty(value = "对应的出仓单id")
    @TableField("dovecote_out_bill")
    private Long dovecoteOutBill;

    //类型
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    private Long typeId;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("type_name")
    private String typeName;


    //数量
    @ApiModelProperty(value = "数量")
    @TableField("amount")
    private Integer amount;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
}
