package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-20-21:30
 */
@Data
public class FeedStockDto {
    private static final long serialVersionUID = 1L;

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

}
