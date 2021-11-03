package com.dove.breed.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.FeedHistory;
import com.dove.breed.entity.vo.FeedHistoryVo;

import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-22-11:15
 */
public interface FeedHistoryService extends IService<FeedHistory> {

    List<FeedHistoryVo> listByType(Long baseId, String dovecoteNumber, String feedNumber, String operator, String startTime, String endTime, Long enterpriseId);

}