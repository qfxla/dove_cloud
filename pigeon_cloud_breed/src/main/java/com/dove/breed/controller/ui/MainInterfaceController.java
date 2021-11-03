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
 * @creat 2021-10-22-20:37
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-主界面图")
@RestController
@RequestMapping("/ui/mainInterface")
public class MainInterfaceController {
    @Value("${BASE_UI_URL.mainInterface}")
    public String baseUrl;

    @ApiOperation("基地相关数据统计")
    @GetMapping("baseData")
    public Result baseData(){
        String path = baseUrl + "基地相关数据统计.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("经济产值走势")
    @GetMapping("economicTrend")
    public Result economicTrend(){
        String path = baseUrl + "经济产值走势.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("基地人员分布")
    @GetMapping("personAttribute")
    public Result personAttribute(){
        String path = baseUrl + "基地人员分布.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("深加工产品分类")
    @GetMapping("processCategory")
    public Result processCategory(){
        String path = baseUrl + "深加工产品分类.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("基地肉鸽去向")
    @GetMapping("doveDestination")
    public Result doveDestination(){
        String path = baseUrl + "基地肉鸽去向.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("历年规模")
    @GetMapping("yearScale")
    public Result yearScale(){
        String path = baseUrl + "历年规模.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error("文件不存在或无数据");
    }

    @ApiOperation("养殖基本信息")
    @GetMapping("getBreedingDove")
    public Result getBreedingDove(){
        String path = baseUrl + "养殖基本信息.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }
}
