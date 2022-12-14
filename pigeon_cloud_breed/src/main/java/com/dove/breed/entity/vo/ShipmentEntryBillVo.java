package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-19-20:10
 */
@Data
public class ShipmentEntryBillVo{

    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //领取基地id
    @ApiModelProperty(value = "领取基地id")
    @TableField("base_id")
    private Long baseId;

    //进库时间
    @ApiModelProperty(value = "进库时间")
    @TableField("in_time")
    private Date inTime;

    //基地名称
    @ApiModelProperty(value = "基地名称")
    @TableField("baseName")
    private String baseName;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //经手人
    @ApiModelProperty(value = "经手人")
    @TableField("handler")
    private String handler;

    //总数量
    @ApiModelProperty(value = "总数量")
    @TableField("amount")
    private Integer amount;

    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total")
    private Integer total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified")
    private Date gmtModified;

}