package com.dove.breed.controller.ui;

import cn.hutool.json.JSONObject;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.mapper.CageRealMapper;
import com.dove.breed.service.CageRealService;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-24-9:53
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-养殖主页")
@RestController
@RequestMapping("/ui/breedPage")
public class BreedPage {
    @Value("${BASE_UI_URL.breedPage}")
    public String baseUrl;
    @Autowired
    private CageRealMapper cageRealMapper;
    @Autowired
    private DovecoteService dovecoteService;
    @Autowired
    private CageRealService cageRealService;

    @ApiOperation("肉鸽出栏曲线图")
    @GetMapping("outOfMeetDove")
    public Result outOfMeetDove(){
        String path = baseUrl + "肉鸽出栏曲线图.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("鸽龄分布")
    @GetMapping("AgeOfDove")
    public Result AgeOfDove(){
        String path = baseUrl + "鸽龄分布.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("生产信息")
    @GetMapping("productionInformation")
    public Result productionInformation(){
        String path = baseUrl + "生产信息.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("鸽笼状态分布")
    @GetMapping("doveStatus")
    public Result doveStatus() {
        String path = baseUrl + "鸽笼状态分布.txt";
        System.out.println(path);
//        List<CageRealVo> layEggsTime = cageRealMapper.getLayEggsTime(3L, "A01");
//        List<CageRealVo> hatchTime = cageRealMapper.getHatchTime(3L, "A01");
//        List<CageRealVo> feedTime = cageRealMapper.getFeedTime(3L, "A01");
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("layTime",layEggsTime.size());
//        map.put("hatchTime",hatchTime.size());
//        map.put("feedTime",feedTime.size());
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("操作提醒列表")
    @GetMapping("operationRemind")
    public Result operationRemind(){
        String path = baseUrl + "操作提醒列表.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("异常排序")
    @GetMapping("getMaxAbnormal")
    public Result getMaxAbnormal() throws InterruptedException {
//        List<CageRealVo> list = dovecoteService.getMaxAbnormal(3L, "A01", 1, 3);
        String path = baseUrl + "异常排序.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("鸽笼图")
    @GetMapping("cageInfo")
    public Result cageInfo() throws InterruptedException {
        List<CageRealVo> list = cageRealService.getAllCage(3L, "A01");
        return Result.success("获取成功").data(list);
    }
}
