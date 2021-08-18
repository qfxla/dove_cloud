package com.dove.processing.entity.Dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 加工厂表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dove_processing")
@ApiModel(value = "DoveProcessing对象", description = "加工厂表")
public class DoveProcessingDto extends Model<DoveProcessingDto> {

    private static final long serialVersionUID = 1L;

    //加工厂id
    @ApiModelProperty(value = "加工厂id")
    @TableId(value = "processing_id", type = IdType.ASSIGN_UUID)
    private Long processingId;

    //加工厂所属企业id
    @ApiModelProperty(value = "加工厂所属企业id")
    @TableField("enterprise_id")
    private Long enterpriseId;

    //加工厂名称
    @ApiModelProperty(value = "加工厂名称")
    @TableField("name")
    private String name;

    //加工厂负责人姓名
    @ApiModelProperty(value = "加工厂负责人姓名")
    @TableField("principal_name")
    private String principalName;

    //加工厂简介
    @ApiModelProperty(value = "加工厂简介")
    @TableField("introduction")
    private String introduction;

    //加工厂所在省份（默认广东省）
    @ApiModelProperty(value = "加工厂所在省份（默认广东省）")
    @TableField("provice")
    private String provice;

    //加工厂所在市区（默认梅州市）
    @ApiModelProperty(value = "加工厂所在市区（默认梅州市）")
    @TableField("city")
    private String city;

    //加工厂所在详细地址
    @ApiModelProperty(value = "加工厂所在详细地址")
    @TableField("detailed_address")
    private String detailedAddress;

    //加工厂图片
    @ApiModelProperty(value = "加工厂图片")
    @TableField("picture")
    private String picture;

    //加工厂视频
    @ApiModelProperty(value = "加工厂视频")
    @TableField("video")
    private String video;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;


}
