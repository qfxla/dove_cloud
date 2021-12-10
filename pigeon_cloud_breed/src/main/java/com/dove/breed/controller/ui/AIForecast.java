package com.dove.breed.controller.ui;

import com.dove.breed.utils.GetFileData;
import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zcj
 * @creat 2021-12-04-16:41
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-AI预测预警")
@RestController
@RequestMapping("/ui/aiForecast")
public class AIForecast {
    @Value("${BASE_UI_URL.aiForecast}")
    public String baseUrl;

    @ApiOperation("预警提醒")
    @GetMapping("forecastRemind")
    public Result outOfMeetDove(){
        String path = baseUrl + "预警提醒.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }
}
