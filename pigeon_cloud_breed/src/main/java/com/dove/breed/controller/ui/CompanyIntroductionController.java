package com.dove.breed.controller.ui;

import com.alibaba.fastjson.JSONObject;
import com.dove.breed.entity.Dovecote;
import com.dove.breed.service.DovecoteService;
import com.dove.breed.utils.GetFileData;
import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-09-20:20
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-公司介绍图")
@RestController
@RequestMapping("/ui/companyIntroduction")
public class CompanyIntroductionController {

    @Autowired
    public DovecoteService dovecoteService;

    @Value("${BASE_UI_URL.companyIntroduction}")
    public String baseUrl;

    @ApiOperation("鸽棚数量，肉鸽出栏数等")
    @GetMapping("getAmountOfDovecote")
    public Result getAmountOfDovecote(){
        String path = baseUrl + "鸽棚数量，肉鸽出栏数等.txt";
        List<Object> objects = GetFileData.getJsonObject(path);
        //添加一个鸽棚数量
        List<Dovecote> list = dovecoteService.list(null);
        Object o = objects.get(0);
        JSONObject jsonObject = JSONObject.parseObject(o.toString());
        jsonObject.put("amountOfDovecote",list.size());
        return Result.success().data(jsonObject);
    }

    @ApiOperation("经济产值走势")
    @GetMapping("getEconomicTendency")
    public Result getEconomicTendency(){
        String path = baseUrl + "经济产值走势.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("人员分布")
    @GetMapping("getPersonDistribution")
    public Result getPersonDistribution(){
        String path = baseUrl + "人员分布.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("肉鸽去向饼状图")
    @GetMapping("getDoveGo")
    public Result getDoveGo(){
        String path = baseUrl + "肉鸽去向饼状图.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("种鸽，乳鸽历年规模")
    @GetMapping("getScaleOfElseYear")
    public Result getScaleOfElseYear(){
        String path = baseUrl + "种鸽，乳鸽历年规模.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("总经济产值，养殖企业，科技投入")
    @GetMapping("getEconomicOutput")
    public Result getEconomicOutput(){
        String path = baseUrl + "总经济产值，养殖企业，科技投入.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }
}
