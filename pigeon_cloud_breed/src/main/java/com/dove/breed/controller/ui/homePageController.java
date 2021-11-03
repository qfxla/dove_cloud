package com.dove.breed.controller.ui;

import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.mapper.CageRealMapper;
import com.dove.breed.mapper.ManualIncubationMapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zcj
 * @creat 2021-10-23-10:52
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-主页图")
@RestController
@RequestMapping("/ui/homePage")
public class homePageController {
    @Value("${BASE_UI_URL.homePage}")
    public String baseUrl;

    @Autowired
    private CageRealMapper cageRealMapper;
    @Autowired
    private ManualIncubationMapper manualIncubationMapper;

    @ApiOperation("肉鸽出栏曲线图")
    @GetMapping("outOfMeetDove")
    public Result outOfMeetDove(){
        String path = baseUrl + "肉鸽出栏曲线图.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("异常蛋曲线图")
    @GetMapping("abnormalEggs")
    public Result abnormalEggs(){
        String path = baseUrl + "异常蛋曲线图.txt";
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
        String path = baseUrl + "鸽笼状态.txt";
        System.out.println(path);
        List<CageRealVo> layEggsTime = cageRealMapper.getLayEggsTime(3L, "A01");
        List<CageRealVo> hatchTime = cageRealMapper.getHatchTime(3L, "A01");
        List<CageRealVo> feedTime = cageRealMapper.getFeedTime(3L, "A01");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("layTime",layEggsTime.size());
        map.put("hatchTime",hatchTime.size());
        map.put("feedTime",feedTime.size());
        return Result.success("获取成功").data(map);
    }

    @ApiOperation("孵化机记录分布")
    @GetMapping("incubatorData")
    public Result incubatorData(){
        String path = baseUrl + "孵化机记录.txt";
        System.out.println(path);
        Map<String, Integer> map = manualIncubationMapper.uiGetDataOfShipToday(3L);
        return Result.success("获取成功").data(map);
    }

    @ApiOperation("基地人员分布")
    @GetMapping("personAttribute")
    public Result personAttribute(){
        String path = baseUrl + "基地人员分布.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }
}
