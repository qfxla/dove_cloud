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
 * 清粪信息表
 * </p>
 *
 * @author zcj
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_clear_soil")
@ApiModel(value = "ClearSoil对象", description = "清粪信息表")
public class ClearSoilDto implements Serializable {

    private static final long serialVersionUID = 1L;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    //基地编号
    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    //清理时间
    @ApiModelProperty(value = "清理时间")
    private Date clearTime;

    //重量
    @ApiModelProperty(value = "重量")
    private Integer weight;

    //重量的单位
    @ApiModelProperty(value = "重量的单位")
    private String type;

    //操作人
    @ApiModelProperty(value = "操作人")
    private String operator;


}