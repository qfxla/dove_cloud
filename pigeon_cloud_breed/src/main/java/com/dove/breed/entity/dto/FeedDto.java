package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 投喂信息表
 * </p>
 *
 * @author zcj
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_feed")
@ApiModel(value = "Feed对象", description = "投喂信息表")
public class FeedDto implements Serializable {

    private static final long serialVersionUID = 1L;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    //基地编号
    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    //饲料名
    @ApiModelProperty(value = "饲料名")
    private String name;

    //饲料规格
    @ApiModelProperty(value = "饲料规格")
    private String type;

    //使用数量
    @ApiModelProperty(value = "使用数量")
    private Integer number;

    //操作人
    @ApiModelProperty(value = "操作人")
    private String operator;


}