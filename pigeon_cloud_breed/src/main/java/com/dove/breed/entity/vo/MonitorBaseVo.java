package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 摄像头信息
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@Data
public class MonitorBaseVo {
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "视频名称")
    private String videoName;

    @ApiModelProperty(value = "视频类型")
    private Integer type;

    @ApiModelProperty(value = "摄像头监控位置")
    private String monitoringLocation;

    @ApiModelProperty(value = "视频路径")
    private String url;

    @ApiModelProperty(value = "视频路径")
    private String accessToken;

    @ApiModelProperty(value = "视频路径")
    private Integer templete;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "设备序列号")
    private String deviceSerial;

    @ApiModelProperty(value = "管道序号")
    private Integer aisle;

    @ApiModelProperty(value = "设备验证码")
    private String validateCode;

    @ApiModelProperty(value = "设备使用状态")
    private Integer statusCode;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}