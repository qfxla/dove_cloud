package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 摄像头信息
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@Data
public class MonitorBaseDto{

    private static final long serialVersionUID = 1L;

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
}