package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class DrinkingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    //基地编号
    @ApiModelProperty(value = "基地编号")
    private Long baseId;

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