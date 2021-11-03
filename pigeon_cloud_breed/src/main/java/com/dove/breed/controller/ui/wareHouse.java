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
 * @creat 2021-10-24-10:53
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-仓储图")
@RestController
@RequestMapping("/ui/wareHouse")
public class wareHouse {
    @Value("${BASE_UI_URL.wareHouse}")
    public String baseUrl;

    @ApiOperation("饲料当前库存")
    @GetMapping("feedStock")
    public Result feedStock(){
        String path = baseUrl + "饲料当前库存.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("营养品当前库存")
    @GetMapping("nourishmentStock")
    public Result nourishmentStock(){
        String path = baseUrl + "营养品当前库存.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("饲料消耗曲线")
    @GetMapping("consumeFeed")
    public Result consumeFeed(){
        String path = baseUrl + "饲料消耗曲线.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("营养品消耗曲线")
    @GetMapping("consumeNourishment")
    public Result consumeNourishment(){
        String path = baseUrl + "营养品消耗曲线.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }
}
