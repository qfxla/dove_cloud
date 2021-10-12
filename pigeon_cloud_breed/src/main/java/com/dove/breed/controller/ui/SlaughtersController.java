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
 * @creat 2021-10-11-23:23
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-屠宰加工储运")
@RestController
@RequestMapping("/ui/slaughters")
public class SlaughtersController{
    @Value("${BASE_UI_URL.slaughters}")
    public String baseUrl;

    @ApiOperation("加工厂人员分布")
    @GetMapping("personnelDistributionOfProcess")
    public Result getBreedingDove(){
        String path = baseUrl + "加工厂人员分布.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("加工数量统计")
    @GetMapping("amountOfProcess")
    public Result amountOfProcess(){
        String path = baseUrl + "加工数量统计.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("储运状态")
    @GetMapping("transportation")
    public Result transportation(){
        String path = baseUrl + "储运状态.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("屠宰厂人员分布")
    @GetMapping("personnelDistributionOfSlaughter")
    public Result personnelDistributionOfSlaughter(){
        String path = baseUrl + "屠宰厂人员分布.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("历史屠宰数量统计（近30天）")
    @GetMapping("amountOfMonth")
    public Result amountOfMonth(){
        String path = baseUrl + "历史屠宰数量统计（近30天）.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("历史屠宰数量统计（近1年）")
    @GetMapping("amountOfYear")
    public Result amountOfYear(){
        String path = baseUrl + "历史屠宰数量统计（近1年）.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");

    }
}
