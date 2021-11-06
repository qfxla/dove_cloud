package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
public class FeedHistoryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    @ApiModelProperty(value = "投喂机编号")
    private String machineNumber;

    @ApiModelProperty(value = "饲料名")
    private String name;

    @ApiModelProperty(value = "饲料规格")
    private String type;

    @ApiModelProperty(value = "使用数量")
    private Integer number;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "操作人")
    private String operator;

}
