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
    @ApiModelProperty(value = "所属鸽棚id")
    @TableField("dovecote_id")
    private Long dovecoteId;

    //饲料类型
    @ApiModelProperty(value = "饲料类型")
    @TableField("feed_type")
    private String feedType;

    //剩余数量
    @ApiModelProperty(value = "剩余数量")
    @TableField("amount")
    private Integer amount;

}
