package com.dove.breed.entity.dto;

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
 * 饮水信息表
 * </p>
 *
 * @author zcj
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_drinking")
@ApiModel(value = "Drinking对象", description = "饮水信息表")
public class DrinkingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    //基地编号
    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "投喂机编号,不能重复")
    private String machineNumber;

    //开始时间
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    //结束时间
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    //操作人
    @ApiModelProperty(value = "操作人")
    private String operator;
}