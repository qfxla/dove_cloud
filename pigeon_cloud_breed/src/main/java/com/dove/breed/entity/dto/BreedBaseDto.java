package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-30-19:59
 */
@Data
public class BreedBaseDto {
    //养殖基地所属企业id
    @ApiModelProperty(value = "养殖基地所属企业id")
    @TableField("enterprise_id")
    private Long enterpriseId;

    //养殖基地名称
    @ApiModelProperty(value = "养殖基地名称")
    @TableField("name")
    private String name;

    //养殖基地负责人名称
    @ApiModelProperty(value = "养殖基地负责人名称")
    @TableField("principal_name")
    private String principalName;

    //养殖基地简介
    @ApiModelProperty(value = "养殖基地简介")
    @TableField("introduction")
    private String introduction;

    //养殖基地所在省份
    @ApiModelProperty(value = "养殖基地所在省份")
    @TableField("province")
    private String province;

    //养殖基地所在省份
    @ApiModelProperty(value = "养殖基地所在省份")
    @TableField("city")
    private String city;

    //养殖基地所在详细地址
    @ApiModelProperty(value = "养殖基地所在详细地址")
    @TableField("detailed_address")
    private String detailedAddress;

}
