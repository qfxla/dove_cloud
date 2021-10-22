package com.dove.breed.controller;


import com.dove.breed.entity.ForecastWarning;
import com.dove.breed.entity.dto.ForecastWarningDto;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 预测预警表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-10-21
 */
@Api(tags = "预测预警")
@RestController
@RequestMapping("/breed/forecast-warning")
public class ForecastWarningController {

    @ApiModelProperty(value = "添加")
    @PostMapping("save")
    public Result save(ForecastWarningDto forecastWarningDto,
                       MultipartFile picture,
                       MultipartFile video){
        System.out.println(forecastWarningDto);
        return null;
    }
}

