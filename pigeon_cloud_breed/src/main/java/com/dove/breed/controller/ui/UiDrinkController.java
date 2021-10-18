package com.dove.breed.controller.ui;

import com.dove.breed.utils.GetFileData;
import com.dove.entity.Result;
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
 * @creat 2021-10-11-14:56
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-饮水图")
@RestController
@RequestMapping("/ui/drink")
public class UiDrinkController {
    @Value("${BASE_UI_URL.drink}")
    private String baseUrl;

    @ApiOperation("周平均淡水消耗历史曲线图")
    @GetMapping("/weekAveConsumeGraph")
    public Result weekAveConsumeGraph(){
        String path = baseUrl + "周平均淡水消耗历史曲线图.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("饮水提醒列表")
    @GetMapping("/remainDrink")
    public Result remainDrink(){
        String path = baseUrl + "饮水提醒列表.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("本月营养补充统计")
    @GetMapping("/monthStatistics")
    public Result monthStatistics(){
        String path = baseUrl + "本月营养补充统计.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }
}
