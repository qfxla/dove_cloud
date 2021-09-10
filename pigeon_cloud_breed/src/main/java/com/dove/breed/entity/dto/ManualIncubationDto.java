package com.dove.breed.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-09-09-20:10
 */
@Data
public class ManualIncubationDto {
    @ApiModelProperty(value = "基地id")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;


    @ApiModelProperty(value = "入孵数量")
    private Integer one;

    @ApiModelProperty(value = "光蛋数量")
    private Integer two;

    @ApiModelProperty(value = "出仔数量")
    private Integer three;

    @ApiModelProperty(value = "回头蛋数量")
    private Integer four;

    @ApiModelProperty(value = "时段(上午下午)")
    private String timeFrame;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "人工填写的时间")
    private Date laborTime;
}
