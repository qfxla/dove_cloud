package com.dove.breed.controller;

import com.alibaba.fastjson.JSONObject;
import com.dove.breed.entity.vo.InternetDeviceData;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xbx
 */
@Api(tags = "物联设备")
@CrossOrigin
@RestController
@RequestMapping("/breed/InternetEquipment")
public class InternetEquipment {

    private RestTemplate restTemplate = new RestTemplate();
    @Resource
    private RedisTemplate redisTemplate = new RedisTemplate();

    private static final String EQUIPMENT_URL = "https://admin.stdag.cn/back3Api/data/collection/v2/latest?device_id=303847123235313844003400&device_type=2&gate_id=&node_id=&sensor_version=";

    @GetMapping("deviceData")
    public Result getDeviceData(){
        String access_token = (String)redisTemplate.opsForValue().get("InternetEquipmentToken");

        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Authorization","Bearer " + access_token);

        HttpEntity<Map<String,Object>> mapHttpEntity = new HttpEntity<>(headers1);

        ResponseEntity<JSONObject> entity = restTemplate.exchange(EQUIPMENT_URL,
                HttpMethod.GET, mapHttpEntity, JSONObject.class);

        JSONObject body = entity.getBody();
        JSONObject data = body.getJSONObject("data");

        Date date = new Date();
        date.setTime(Long.valueOf(data.getString("0")+"000"));

        InternetDeviceData internetDeviceData = new InternetDeviceData();
        internetDeviceData.setDevicePictureUrl(data.getString("11"));
        internetDeviceData.setCaptureTime(date);
        internetDeviceData.setTemperature(data.getString("1") + "℃");
        internetDeviceData.setRH(data.getString("2") + "%");
        internetDeviceData.setAtmosphericPressure(data.getString("3") + "hPa");
        internetDeviceData.setLightIntensity(data.getString("4") + "lux");

        return Result.success().data(internetDeviceData);
    }
}
