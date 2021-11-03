package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zcj
 * @creat 2021-10-22-11:04
 */
@Data
@TableName("t_feed_history")
@ApiModel(value="FeedHistory对象", description="")
public class FeedHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

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

    @ApiModelProperty(value = "操作人")
    private String operator;

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
