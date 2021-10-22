package com.dove.breed.config;

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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-09-02-10:00
 */
@Component
@Slf4j
public class ScheduledTask {
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

//    /**
//     * 自动扫描，启动时间点之后每周执行一次
//     */
//    @Scheduled(cron = "* * * * * 1 ")
//    public void updateVideoToken(){
//        monitorBaseService.updateToken();
//    }
}
