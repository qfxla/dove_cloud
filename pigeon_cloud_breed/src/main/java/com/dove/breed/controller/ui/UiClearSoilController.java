package com.dove.breed.controller.ui;

import cn.hutool.json.JSONObject;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-11-15:13
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-清粪图")
@RestController
@RequestMapping("/ui/clearSoil")
public class UiClearSoilController {
    @Value("${BASE_UI_URL.clearSoil}")
    private String baseUrl;

    @ApiOperation("鸽粪机运转参数")
    @GetMapping("/runningRate")
    public Result runningRate(){
        String path = baseUrl + "鸽粪机运转参数.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("清粪提醒列表")
    @GetMapping("/remainClearSoil")
    public Result remainClearSoil(){
        String url = baseUrl + "清粪提醒列表.txt";
//        List<Object> jsonObject = GetFileData.getJsonObject(url);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("time",df.format(today));
        jsonObject1.put("status","completed");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("time",df.format(new Date(today.getTime() + 3 * 24 * 60 * 60 * 1000)));
        jsonObject2.put("status","unCompleted");

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("time",df.format(new Date(today.getTime() - 3 * 24 * 60 * 60 * 1000)));
        jsonObject3.put("status","Completed");

        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("time",df.format(new Date(today.getTime() - 6 * 24 * 60 * 60 * 1000)));
        jsonObject4.put("status","Completed");

        List<JSONObject> list = new ArrayList<>();
        return list.size() > 0?Result.success("获取成功").data(list) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("月鸽粪统计")
    @GetMapping("/monthStatistics")
    public Result monthStatistics(){
        String url = baseUrl + "月鸽粪统计.txt";
        List<Object> jsonObject = GetFileData.getJsonObject(url);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }
}
