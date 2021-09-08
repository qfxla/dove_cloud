package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-30-19:57
 */
@Data
public class BreedBaseVo {
    private Long id;

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

    //养殖基地图片
    @ApiModelProperty(value = "养殖基地图片")
    @TableField("picture")
    private String picture;

    //养殖基地视频
    @ApiModelProperty(value = "养殖基地视频")
    @TableField("video")
    private String video;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
