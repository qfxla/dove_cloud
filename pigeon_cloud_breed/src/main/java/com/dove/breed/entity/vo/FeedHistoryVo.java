package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: xbx
 */
@Data
public class FeedHistoryVo {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    @ApiModelProperty(value = "投喂机编号")
    private String feedNumber;

    @ApiModelProperty(value = "饲料名")
    private String name;

    @ApiModelProperty(value = "饲料规格")
    private String type;

    @ApiModelProperty(value = "使用数量")
    private Integer number;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "创造时间")
    private Date gmtCreate;
}