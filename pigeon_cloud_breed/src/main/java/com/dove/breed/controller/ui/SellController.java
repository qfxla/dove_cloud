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
 * @creat 2021-10-12-10:13
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-销售图")
@RestController
@RequestMapping("/ui/sell")
public class SellController {
    @Value("${BASE_UI_URL.sell}")
    public String baseUrl;

    @ApiOperation("大型商超当月销量")
    @GetMapping("sellAmountOfSuperMarket")
    public Result getBreedingDove(){
        String path = baseUrl + "大型商超当月销量.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("饭店当月销量统计")
    @GetMapping("sellAmountOfRestaurant")
    public Result sellAmountOfRestaurant(){
        String path = baseUrl + "饭店当月销量统计.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("线上商超当月销量")
    @GetMapping("sellAmountOfOnlineSuper")
    public Result sellAmountOfOnlineSuper(){
        String path = baseUrl + "线上商超当月销量.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("农贸市场当月销量")
    @GetMapping("sellAmountOfFarmMarket")
    public Result sellAmountOfFarmMarket(){
        String path = baseUrl + "农贸市场当月销量.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("销售市场分布")
    @GetMapping("salesMarketDistribution")
    public Result salesMarketDistribution(){
        String path = baseUrl + "销售市场分布.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }
}
