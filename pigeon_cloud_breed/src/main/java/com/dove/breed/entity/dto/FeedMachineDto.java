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
    private String feedNumber;

    @ApiModelProperty(value = "转速")
    private String rev;

    @ApiModelProperty(value = "开关")
    private Integer isOpen;

    @ApiModelProperty(value = "方向")
    private Integer direction;

}