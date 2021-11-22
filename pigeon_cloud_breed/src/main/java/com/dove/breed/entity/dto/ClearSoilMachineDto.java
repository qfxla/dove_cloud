package com.dove.breed.entity.dto;

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
public class ClearSoilMachineDto implements Serializable {

    private static final long serialVersionUID = 1L;

    //基地编号
    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    //清粪机编号
    @ApiModelProperty(value = "清粪机编号")
    private String machineNumber;

    @ApiModelProperty(value = "设备名称")
    private String device_name;

    @ApiModelProperty(value = "设备品牌")
    private String brand;

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

}
