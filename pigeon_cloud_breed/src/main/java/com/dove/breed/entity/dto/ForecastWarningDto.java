package com.dove.breed.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-10-21-15:00
 */
@Data
public class ForecastWarningDto {
    @ApiModelProperty(value = "基地id")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "鸽笼id")
    private Long cageId;

//    private MultipartFile picture;
//
//    private MultipartFile video;

    @ApiModelProperty(value = "视频名称")
    private String videoName;

    @ApiModelProperty(value = "事件起始时间")
    private Date startTime;

    @ApiModelProperty(value = "事件结束时间")
    private Date endTime;

    @ApiModelProperty(value = "信息(鸽子受惊..)")
    private String information;

    @ApiModelProperty(value = "企业id")
    private Long guige;
}
