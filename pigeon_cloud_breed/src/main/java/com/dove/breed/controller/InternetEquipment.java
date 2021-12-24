package com.dove.breed.controller;

import com.alibaba.fastjson.JSONObject;
import com.dove.breed.entity.vo.InternetDeviceData;
import com.dove.breed.utils.ChineseCharDateEnum;
import com.dove.breed.utils.GetMonth;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Calendar;
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

    @ApiOperation(value = "查找物联设备数据")
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

//    @ApiOperation(value = "查找传感器数据周")
//    @GetMapping("EnvirMonGraphDataWeek")
//    public JSONObject getEnvirMonGraphDataWeek(){
//        String url = "https://backend.farmapi.xiaomaiot.com/v2/device_chunk/26054/pm10?start_time={start_time}&stop_time={stop_time}&time_interval={time_interval}";
//
//        String access_token = (String)redisTemplate.opsForValue().get("EnvirMonGraphToken");
//
//        HttpHeaders headers1 = new HttpHeaders();
//        headers1.add("token", access_token);
//
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.DATE, -7);
//        Map<String, Object> map = new HashMap<>();
//        map.put("start_time", GetMonth.getTimeExactSecond(c.getTime()));
//        map.put("stop_time", GetMonth.getTimeExactSecond(new Date()));
//        map.put("time_interval", "1h");
//
//        HttpEntity<Map<String,Object>> mapHttpEntity = new HttpEntity<>(headers1);
//
//        ResponseEntity<JSONObject> entity = restTemplate.exchange(url,
//                HttpMethod.GET, mapHttpEntity, JSONObject.class, map);
//
//        return entity.getBody();
//    }

    @ApiOperation(value = "查找传感器历史数据")
    @GetMapping("EnvirMonGraphDataHistory/{devId}/{dateType}/{timeType}")
    public JSONObject getEnvirMonGraphDataDays(@PathVariable("devId") int devId,
                                               @PathVariable("dateType") String dateType,
                                               @PathVariable("timeType") String timeType){
        String url = "https://backend.farmapi.xiaomaiot.com/v2/device_chunk/"+devId+"/"+dateType+"/days?start_time={start_time}&stop_time={stop_time}&time_interval={time_interval}";

        String access_token = (String)redisTemplate.opsForValue().get("EnvirMonGraphToken");

        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("token", access_token);

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String timeInterval  = "20m";
        if(ChineseCharDateEnum.CHAR_DATE_ENUM.getDateName().equals(timeType)) {
            c.add(Calendar.DATE, -1);
        }else if(ChineseCharDateEnum.CHAR_WEEK_ENUM.getDateName().equals(timeType)) {
            c.add(Calendar.DAY_OF_WEEK, -1);
            timeInterval = "1h";
        }else if(ChineseCharDateEnum.CHAR_MONTH_ENUM.getDateName().equals(timeType)) {
            c.add(Calendar.MONTH, -1);
            timeInterval = "8h";
        }else if(ChineseCharDateEnum.CHAR_YEAR_ENUM.getDateName().equals(timeType)) {
            c.add(Calendar.YEAR, -1);
            timeInterval = "1d";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("start_time", GetMonth.getTimeExactSecond(c.getTime()));
        map.put("stop_time", GetMonth.getTimeExactSecond(new Date()));
        map.put("time_interval", timeInterval);

        HttpEntity<Map<String,Object>> mapHttpEntity = new HttpEntity<>(headers1);

        ResponseEntity<JSONObject> entity = restTemplate.exchange(url,
                HttpMethod.GET, mapHttpEntity, JSONObject.class, map);

        return entity.getBody();
    }

    @ApiOperation(value = "传感器实时数据")
    @GetMapping("sensorValue")
    public Result getSensorValue(){
        String url = "https://backend.farmapi.xiaomaiot.com/farm/696/end_data/list";
        JSONObject forObject = restTemplate.getForObject(url, JSONObject.class);
        return Result.success().data(forObject.getJSONArray("data").getJSONObject(3));
    }

}
