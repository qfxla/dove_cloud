package com.dove.breed.controller.ui;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.CagePosition;
import com.dove.breed.entity.ShipmentOutBill;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.mapper.CageRealMapper;
import com.dove.breed.mapper.ManualIncubationMapper;
import com.dove.breed.service.CagePositionService;
import com.dove.breed.service.CageRealService;
import com.dove.breed.utils.GetFileData;
import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    private CagePositionService cagePositionService;
    @Autowired
    private CageRealService cageRealService;

    @ApiOperation("六个鸽笼")
    @GetMapping("doveCage")
    public Result doveCage(){
        List<JSONObject> list = new ArrayList<>();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("status","未产卵");
        jsonObject1.put("pic","/group1/ui/未产卵.png");
        jsonObject1.put("video","/group1/ui/未产卵.mp4");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("status","孵化中");
        jsonObject2.put("pic","/group1/ui/孵化中.png");
        jsonObject2.put("video","/group1/ui/孵化中.mp4");
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("status","产蛋中");
        jsonObject3.put("pic","/group1/ui/产蛋中.png");
        jsonObject3.put("video","/group1/ui/产蛋中.mp4");
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("status","哺育1天");
        jsonObject4.put("pic","/group1/ui/哺育1天.png");
        jsonObject4.put("video","/group1/ui/哺育1天.mp4");
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("status","哺育3天");
        jsonObject5.put("pic","/group1/ui/哺育3天.png");
        jsonObject5.put("video","/group1/ui/哺育3天.mp4");
        JSONObject jsonObject6 = new JSONObject();
        jsonObject6.put("status","哺育7天");
        jsonObject6.put("pic","/group1/ui/哺育7天.png");
        jsonObject6.put("video","/group1/ui/哺育7天.mp4");
        JSONObject jsonObject7 = new JSONObject();
        jsonObject7.put("status","哺育15天");
        jsonObject7.put("pic","/group1/ui/哺育15天.png");
        jsonObject7.put("video","/group1/ui/哺育15天.mp4");
        JSONObject jsonObject8 = new JSONObject();
        jsonObject8.put("status","哺育20天");
        jsonObject8.put("pic","/group1/ui/哺育20天.png");
        jsonObject8.put("video","/group1/ui/哺育20天.mp4");
        JSONObject jsonObject9 = new JSONObject();
        jsonObject9.put("status","哺育25天");
        jsonObject9.put("pic","/group1/ui/哺育25天.png");
        jsonObject9.put("video","/group1/ui/哺育25天.mp4");

        list.add(jsonObject1);
        list.add(jsonObject2);
        list.add(jsonObject3);
        list.add(jsonObject4);
        list.add(jsonObject5);
        list.add(jsonObject6);
        list.add(jsonObject7);
        list.add(jsonObject8);
        list.add(jsonObject9);

        List<JSONObject> randoms = createRandoms(list, 6);
        return Result.success("获取成功").data(randoms);
    }

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
        return Result.success("获取成功").data(jsonObject);
    }

    @ApiOperation("孵化机记录分布")
    @GetMapping("incubatorData")
    public Result incubatorData(){
        String path = baseUrl + "孵化机记录.txt";
        System.out.println(path);
//        Map<String, Integer> map = manualIncubationMapper.uiGetDataOfShipToday(3L);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return Result.success("获取成功").data(jsonObject);
    }

    @ApiOperation("基地人员分布")
    @GetMapping("personAttribute")
    public Result personAttribute(){
        String path = baseUrl + "基地人员分布.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("告警记录")
    @GetMapping("alarmRecord")
    public Result alarmRecord(){
        String path = baseUrl + "告警记录.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("风险评估")
    @GetMapping("riskAssessment")
    public Result riskAssessment(){
        String path = baseUrl + "风险评估.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    protected List<JSONObject> createRandoms(List<JSONObject> list, int n) {
        Map<Integer,String> map = new HashMap();
        List<JSONObject> news = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random = (int)(Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    news.add(list.get(random));
                }
            }
            return news;
        }
    }
}
