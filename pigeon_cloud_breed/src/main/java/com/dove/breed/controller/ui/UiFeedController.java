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
 * @creat 2021-10-11-14:01
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-喂养图")
@RestController
@RequestMapping("/ui/feed")
public class UiFeedController {
    @Value("${BASE_UI_URL.feed}")
    public String baseUrl;

    @ApiOperation("周平均饲料消耗历史曲线图")
    @GetMapping("/useOfFeedOnWeek")
    public Result useOfFeedOnWeek(){
        String path = baseUrl + "周平均饲料消耗历史曲线图.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("本月投喂统计")
    @GetMapping("/monthStatistics")
    public Result parameter(){
        String path = baseUrl + "本月投喂统计.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("投喂提醒列表")
    @GetMapping("/feedRemain")
    public Result remain(){
        String path = baseUrl + "投喂提醒列表.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

}
