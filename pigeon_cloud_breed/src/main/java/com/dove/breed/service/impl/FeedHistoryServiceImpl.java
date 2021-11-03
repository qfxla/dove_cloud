package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.entity.FeedHistory;
import com.dove.breed.entity.vo.FeedHistoryVo;
import com.dove.breed.mapper.FeedHistoryMapper;
import com.dove.breed.service.FeedHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-22-11:17
 */
@Service
public class FeedHistoryServiceImpl extends ServiceImpl<FeedHistoryMapper, FeedHistory> implements FeedHistoryService {

    @Resource
    private FeedHistoryMapper feedHistoryMapper;
    @Override
    public List<FeedHistoryVo> listByType(Long baseId, String dovecoteNumber, String feedNumber, String operator, String startTime, String endTime, Long enterpriseId) {
        return feedHistoryMapper.listByType(baseId, dovecoteNumber, feedNumber, operator, startTime, endTime, enterpriseId);
    }
}