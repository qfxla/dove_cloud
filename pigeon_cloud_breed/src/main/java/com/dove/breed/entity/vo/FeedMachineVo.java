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

/**
 * <p>
 * 投喂信息表
 * </p>
 *
 * @author zcj
 * @since 2021-10-12
 */
@Data
@TableName("t_feed_machine")
@ApiModel(value="FeedMachine对象", description="投喂机信息表")
public class FeedMachineVo implements Serializable {

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


    @ApiModelProperty(value = "投喂机编号")
    private String feedNumber;

    //饲料名
    @ApiModelProperty(value = "饲料名")
    private String name;

    //饲料规格
    @ApiModelProperty(value = "饲料规格")
    private String type;

    //使用数量
    @ApiModelProperty(value = "使用数量")
    private Integer number;

    @ApiModelProperty(value = "转速")
    private String rev;

    @ApiModelProperty(value = "开关")
    private Integer isOpen;

    @ApiModelProperty(value = "方向")
    private Integer direction;
}