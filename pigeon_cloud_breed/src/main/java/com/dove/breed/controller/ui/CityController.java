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
 * @creat 2021-10-09-20:39
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-梅州市图")
@RestController
@RequestMapping("/ui/city")
public class CityController {
    @Value("${BASE_UI_URL.city}")
    public String baseUrl;

    @ApiOperation("养殖基本信息")
    @GetMapping("getBreedingDove")
    public Result getBreedingDove(){
        String path = baseUrl + "c_养殖基本信息.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }


    @ApiOperation("总经济产值，养殖企业，从业人员")
    @GetMapping("getEconomicAndStaffs")
    public Result getEconomicAndStaffs(){
        String path = baseUrl + "c_总经济产值，养殖企业，从业人员.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("销售额走势")
    @GetMapping("consumeTrend")
    public Result consumeTrend(){
        String path = baseUrl + "c_销售额走势.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("带动农户走势")
    @GetMapping("leadFarmer")
    public Result leadFarmer(){
        String path = baseUrl + "c_带动农户走势.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("种鸽乳鸽存栏")
    @GetMapping("liveStock")
    public Result liveStock(){
        String path = baseUrl + "c_种鸽乳鸽存栏.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("历年规模")
    @GetMapping("yearScale")
    public Result yearScale(){
        String path = baseUrl + "c_历年规模.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }
}
