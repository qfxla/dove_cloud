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
 * @creat 2021-10-09-20:31
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-省份图")
@RestController
@RequestMapping("/ui/province")
public class ProvinceController {
    @Value("${BASE_UI_URL.province}")
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
    @GetMapping("getThreeArea")
    public Result getFiveArea(){
        String path = baseUrl + "c_3个典型区域.txt";
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
}
