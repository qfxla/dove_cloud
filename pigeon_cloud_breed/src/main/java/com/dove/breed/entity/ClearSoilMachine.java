package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zcj
 * @since 2021-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_clear_soil_machine")
@ApiModel(value = "ClearSoilMachine对象", description = "")
public class ClearSoilMachine implements Serializable {

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
    @ApiModelProperty(value = "清粪机编号,不能重复")
    private String machineNumber;

    @ApiModelProperty(value = "设备名称")
    private String device_name;

    @ApiModelProperty(value = "设备品牌")
    private String brand;

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

    //企业id
    @ApiModelProperty(value = "企业id")
    private Long guige;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //更新时间
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    //逻辑删除
    @ApiModelProperty(value = "逻辑删除(0否,1是)")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    //版本号
    @ApiModelProperty(value = "版本号")
    @Version
    private Integer version;


}
