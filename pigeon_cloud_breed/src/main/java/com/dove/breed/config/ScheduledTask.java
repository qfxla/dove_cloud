package com.dove.breed.config;

import com.dove.breed.entity.Dovecote;
import com.dove.breed.mapper.DovecoteMapper;
import lombok.extern.slf4j.Slf4j;
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
    private DovecoteMapper dovecoteMapper;
    /**
     * 自动扫描，启动时间点之后3秒执行一次
     */
    @Scheduled(cron = "1/3 * * * * ? ")
    public void getCurrentDate(){
        log.info("当前时间" + new Date());

    }
}
