package com.dove.breed.config;

import com.alibaba.fastjson.JSONObject;
import com.dove.breed.entity.BreedBase;
import com.dove.breed.entity.Dovecote;
import com.dove.breed.mapper.BreedBaseMapper;
import com.dove.breed.mapper.DovecoteDailyMapper;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.service.DovecoteDailyService;
import com.dove.breed.service.FeedStockService;
import com.dove.breed.service.MonitorBaseService;
import com.dove.breed.utils.Image2Mp4;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FrameRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zcj
 * @creat 2021-09-02-10:00
 */
@Component
@Slf4j
public class ScheduledTask {
    RestTemplate client = new RestTemplate();
    @Autowired
    private DovecoteDailyService dovecoteDailyService;
    @Autowired
    private DovecoteMapper dovecoteMapper;
    @Autowired
    private FeedStockService feedStockService;
    @Autowired
    private Image2Mp4 image2Mp4;
    @Autowired
    private MonitorBaseService monitorBaseService;
    @Resource
    private RedisTemplate redisTemplate;

    private String IETokenUrl = "https://admin.stdag.cn/back3Api/auth/oauth/token?grant_type=web_farm";
    /**
     * 自动扫描，启动时间点之后每天11.30执行一次,鸽笼日结
     */
    @Scheduled(cron = "0 30 23 * * ? ")
    public void getCurrentDate(){
        log.info("当前时间" + new Date());
        List<Dovecote> dovecoteList = dovecoteMapper.selectList(null);
        for (Dovecote dovecote : dovecoteList) {
            dovecoteDailyService.updateDovecoteDaily(dovecote.getBaseId(),dovecote.getDovecoteNumber());
        }
    }

    /**
     *把鸽笼图片转为视频，没晚2点执行
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void addImage2Mp4() throws FrameRecorder.Exception {
        log.info("当前时间" + new Date());
        image2Mp4.updateMp4();
    }


    /**
     * 自动扫描，启动时间点之后每个月执行一次
     */
//    @Scheduled(cron = "0 0 0 ? 1/1 ? ")
    @Scheduled(cron = "0 0 0 1 * ? ")
    public void getCurrentMonth(){
        log.info("月结当前时间" + new Date());
        List<Dovecote> dovecoteList = dovecoteMapper.selectList(null);
        for (Dovecote dovecote : dovecoteList) {
            feedStockService.updateDovecoteMonth(dovecote.getBaseId(),dovecote.getDovecoteNumber());
        }
    }

    /**
     * 自动扫描，启动时间点之后每周执行一次
     */
    @Scheduled(cron = "0 0 0 ? * 1")
    public void updateVideoToken(){
        monitorBaseService.updateToken();
    }

    /**
     * 自动扫描，启动时间点之后每12个小时执行一次
     */
    @Scheduled(cron = "0 0 00,12 * * ?")
    public void updateInternetEquipmentToken(){
        log.info("物联设备token更新时间:" + new Date());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Basic d2ViOnN0ZGFnd2Vi");
        headers.add("Content-Type","multipart/form-data");

        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("phone",13428947489L);
        map.add("password","jinlv12345");
        map.add("login_from",2);
        map.add("platform_type",2);

        HttpEntity<MultiValueMap<String,Object>> httpEntity = new HttpEntity<>(map,headers);

        JSONObject jsonObject = client.postForObject(IETokenUrl, httpEntity, JSONObject.class);

        String InternetEquipmentToken = jsonObject.getString("access_token");

        redisTemplate.opsForValue().set("InternetEquipmentToken",InternetEquipmentToken,12, TimeUnit.HOURS);

    }
}
