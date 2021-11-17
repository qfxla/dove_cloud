package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-09-06-21:36
 */
@Data
public class BaseStockDto {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "基地id")
    private Long baseId;

    @ApiModelProperty(value = "基地名称")
    private String baseName;

    //单位
    @ApiModelProperty(value = "单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "剩余量")
    private Integer amount;

}
