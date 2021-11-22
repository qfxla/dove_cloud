package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: xbx
 */
@Data
public class DrinkingMachineVo {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "饮水机编号")
    private String machineNumber;

    @ApiModelProperty(value = "设备名称")
    private String device_name;

    @ApiModelProperty(value = "设备品牌")
    private String brand;

    @ApiModelProperty(value = "峰值扬程")
    private String peakHead;

    @ApiModelProperty(value = "峰值吸程")
    private String peakSuction;

    @ApiModelProperty(value = "峰值流量")
    private String peakFlow;

    @ApiModelProperty(value = "转速")
    private String rev;

    @ApiModelProperty(value = "启动时间(开启的时候字段显示当前启动时间，关闭的时候显示上一次启动时间)")
    private Date startTime;

    @ApiModelProperty(value = "停止时间（开启的时候字段显示为空，关闭的时候显示上一次停止时间）")
    private Date stopTime;

    @ApiModelProperty(value = "开关（0关，1开）")
    private Boolean open;
}
