package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-30-21:26
 */
@Data
public class BusinessBreedingDto {
    //商家名称
    @ApiModelProperty(value = "商家名称")
    @TableField("name")
    private String name;

    //商家联系方式
    @ApiModelProperty(value = "商家联系方式")
    @TableField("phone")
    private String phone;

    //联系人
    @ApiModelProperty(value = "联系人")
    @TableField("contacts")
    private String contacts;

    //商家类型(供应商/销售客户)
    @ApiModelProperty(value = "商家类型(供应商/销售客户)")
    @TableField("type")
    private String type;
}
