package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 投喂信息表
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
@Data
@TableName("t_feed_machine")
@ApiModel(value="FeedMachine对象", description="投喂机信息表")
public class FeedMachine implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    @ApiModelProperty(value = "投喂机编号,不能重复")
    private String machineNumber;

    @ApiModelProperty(value = "饲料名")
    private String name;

    @ApiModelProperty(value = "饲料规格")
    private String type;

    @ApiModelProperty(value = "使用数量")
    private Integer number;

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

    @ApiModelProperty(value = "开关")
    @TableField("is_open")
    private Boolean open;

    @ApiModelProperty(value = "方向")
    private Integer direction;

    @ApiModelProperty(value = "企业id")
    private Long guige;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "逻辑删除(0否,1是)")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "版本号")
    @Version
    private Integer version;
}
