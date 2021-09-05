package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-20-21:20
 */
@Data
public class FeedStockVo {
    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //所属基地id
    @ApiModelProperty(value = "所属基地id")
    @TableField("base_id")
    private Long baseId;

    //所属鸽棚id
    @ApiModelProperty(value = "所属鸽棚编号")
    @TableField("dovecote_number")
    private String dovecoteNumber;

    //饲料类型
    @ApiModelProperty(value = "饲料类型")
    @TableField("feed_type")
    private String feedType;

    @ApiModelProperty(value = "进购数量")
    @TableField("stock_amount")
    private Integer stockAmount;

    @ApiModelProperty(value = "使用数量")
    @TableField("use_amount")
    private Integer useAmount;

    @ApiModelProperty(value = "剩余数量")
    @TableField("amount")
    private Integer amount;

    @ApiModelProperty(value = "规格")
    @TableField("specifications")
    private String specifications;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
