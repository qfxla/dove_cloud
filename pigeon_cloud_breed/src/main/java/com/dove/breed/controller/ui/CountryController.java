package com.dove.breed.controller.ui;

import com.dove.breed.entity.BaseStock;
import com.dove.breed.entity.dto.BaseStockDto;
import com.dove.breed.utils.GetFileData;
import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-09-15:04
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-国家图")
@RestController
@RequestMapping("/ui/country")
public class CountryController {
    @Value("${BASE_UI_URL.country}")
    public String baseUrl;

    @ApiOperation("肉鸽出栏，种鸽存栏，鸽蛋总量")
    @GetMapping("getBreedingDove")
    public Result getBreedingDove(){
        String path = baseUrl + "c_肉鸽出栏数等.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("各地种乳鸽存栏柱状图")
    @GetMapping("getHistogram")
    public Result getHistogram(){
        String path = baseUrl + "c_各地种乳鸽存栏柱状图.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("5个典型区域")
    @GetMapping("getFiveArea")
    public Result getFiveArea(){
        String path = baseUrl + "c_5个典型区域.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("乳鸽、老鸽、鸽蛋价格走势")
    @GetMapping("getDovePriceTendency")
    public Result getDovePriceTendency(){
        String path = baseUrl + "c_乳鸽、老鸽、鸽蛋价格走势.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("总经济产值，养殖企业，从业人员")
    @GetMapping("getEconomicAndStaffs")
    public Result getEconomicAndStaffs(){
        String path = baseUrl + "c_总经济产值，养殖企业，从业人员.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("经济产值走势")
    @GetMapping("getEconomicTendency")
    public Result getEconomicTendency(){
        String path = baseUrl + "c_经济产值走势.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("小麦和玉米价格走势")
    @GetMapping("getWheatAndCornPrice")
    public Result getWheatAndCornPrice(){
        String path = baseUrl + "c_小麦和玉米价格走势.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

}
