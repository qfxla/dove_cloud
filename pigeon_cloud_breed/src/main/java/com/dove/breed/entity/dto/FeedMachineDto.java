package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 投喂信息表
 * </p>
 *
 * @author zcj
 * @since 2021-10-12
 */
@Data
@ApiModel(value = "FeedMachineDto对象", description = "创建投喂机表")
public class FeedMachineDto implements Serializable {

    private static final long serialVersionUID = 1L;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    //基地编号
    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    @ApiModelProperty(value = "投喂机编号")
    private String machineNumber;

    @ApiModelProperty(value = "设备名称")
    private String device_name;

    @ApiModelProperty(value = "设备品牌")
    private String brand;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "停止时间")
    private Date stopTime;

    @ApiModelProperty(value = "行走速度")
    private String speed;

    @ApiModelProperty(value = "停留时间(秒)")
    private Integer standingTime;

    @ApiModelProperty(value = "维护周期(天)")
    private Integer maintenancePeriod;

    @ApiModelProperty(value = "转速")
    private String rev;

    @ApiModelProperty(value = "方向")
    private Integer direction;

}