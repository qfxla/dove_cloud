package com.dove.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 加工厂表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoveProcessingVo extends Model<DoveProcessingVo> {

    private static final long serialVersionUID = 1L;

    //加工厂id
    @ApiModelProperty(value = "加工厂id")
    @TableId(value = "processing_id")
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
    @TableField("province")
    private String province;

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


}
