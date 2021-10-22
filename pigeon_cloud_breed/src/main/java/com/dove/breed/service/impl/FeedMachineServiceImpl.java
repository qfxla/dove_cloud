package com.dove.breed.service.impl;

import com.dove.breed.entity.FeedHistory;
import com.dove.breed.entity.FeedMachine;
import com.dove.breed.entity.dto.FeedMachineAddFeedDto;
import com.dove.breed.entity.vo.FeedMachineVo;
import com.dove.breed.mapper.FeedHistoryMapper;
import com.dove.breed.mapper.FeedMachineMapper;
import com.dove.breed.service.FeedMachineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 投喂信息表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
@Service
public class FeedMachineServiceImpl extends ServiceImpl<FeedMachineMapper, FeedMachine> implements FeedMachineService {

    @Resource
    private FeedMachineMapper feedMachineMapper;

    @Resource
    private FeedHistoryMapper feedHistoryMapper;

    @Override
    public List<FeedMachineVo> listByType(Long baseId, String dovecoteNumber, Integer isOpen, Long enterpriseId) {
        return feedMachineMapper.listByType(baseId, dovecoteNumber, isOpen, enterpriseId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addFeed(Long id, String operator, FeedMachineAddFeedDto feedMachineAddFeedDto) {
        FeedMachine feedMachine = feedMachineMapper.selectById(id);
        feedMachine.setName(feedMachineAddFeedDto.getName());
        feedMachine.setType(feedMachineAddFeedDto.getType());
        feedMachine.setNumber(feedMachineAddFeedDto.getNumber());

        FeedHistory feedHistory = new FeedHistory();
        feedHistory.setDovecoteNumber(feedMachine.getDovecoteNumber());
        feedHistory.setBaseId(feedMachine.getBaseId());
        feedHistory.setFeedNumber(feedMachine.getFeedNumber());
        feedHistory.setName(feedMachine.getName());
        feedHistory.setType(feedMachine.getType());
        feedHistory.setNumber(feedMachine.getNumber());
        feedHistory.setOperator(operator);
        feedHistory.setGuige(feedMachine.getGuige());

        feedHistoryMapper.insert(feedHistory);
        return feedMachineMapper.updateById(feedMachine) > 0;
    }
}
