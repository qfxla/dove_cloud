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
 * @creat 2021-10-11-15:13
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-清粪图")
@RestController
@RequestMapping("/ui/clearSoil")
public class ClearSoilController {
    @Value("${BASE_UI_URL.clearSoil}")
    private String baseUrl;

    @ApiOperation("鸽粪机运转参数")
    @GetMapping("/runningRate")
    public Result runningRate(){
        String path = baseUrl + "鸽粪机运转参数.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("清粪提醒列表")
    @GetMapping("/remainClearSoil")
    public Result remainClearSoil(){
        String url = baseUrl + "清粪提醒列表.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(url);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("月鸽粪统计")
    @GetMapping("/monthStatistics")
    public Result monthStatistics(){
        String url = baseUrl + "月鸽粪统计.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(url);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }
}
