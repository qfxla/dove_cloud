package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xbx
 */
@Data
public class ClearSoilMachineVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //基地编号
    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    //清粪机编号
    @ApiModelProperty(value = "清粪机编号")
    private String machineNumber;

    //清理重量
    @ApiModelProperty(value = "清理重量")
    private Integer weight;

    //单位
    @ApiModelProperty(value = "单位")
    private String type;

    //启动时间(开启的时候字段显示当前启动时间，关闭的时候显示上一次启动时间)
    @ApiModelProperty(value = "启动时间(开启的时候字段显示当前启动时间，关闭的时候显示上一次启动时间)")
    private Date startTime;

    //停止时间（开启的时候字段显示为空，关闭的时候显示上一次停止时间）
    @ApiModelProperty(value = "停止时间（开启的时候字段显示为空，关闭的时候显示上一次停止时间）")
    private Date stopTime;

    //行走速度
    @ApiModelProperty(value = "行走速度")
    private String speed;

    //停留时间(秒)
    @ApiModelProperty(value = "停留时间(秒)")
    private Integer standingTime;

    //维护周期(天)
    @ApiModelProperty(value = "维护周期(天)")
    private Integer maintenancePeriod;

    //发动机的转速(800/min)
    @ApiModelProperty(value = "发动机的转速(800/min)")
    private String rev;

    //开关（0关，1开）
    @ApiModelProperty(value = "开关")
    @TableField("is_open")
    private Boolean open;
}
