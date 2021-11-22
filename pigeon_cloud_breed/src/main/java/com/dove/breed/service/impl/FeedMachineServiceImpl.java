package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import java.util.Date;
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
    public List<FeedMachineVo> listByType(Long baseId, String dovecoteNumber, Integer open, Long enterpriseId) {
        return feedMachineMapper.listByType(baseId, dovecoteNumber, open, enterpriseId);
    }

    @Override
    public boolean open(String machineNumber, FeedMachineAddFeedDto feedMachineAddFeedDto) {
        QueryWrapper<FeedMachine> wrapper = new QueryWrapper<>();
        wrapper.eq("machine_number",machineNumber);
        FeedMachine feedMachine = feedMachineMapper.selectOne(wrapper);
        if(feedMachine == null || feedMachine.getOpen()) {
            return false;
        }
        if(feedMachineAddFeedDto != null) {
            feedMachine.setName(feedMachineAddFeedDto.getName());
            feedMachine.setType(feedMachineAddFeedDto.getType());
            feedMachine.setNumber(feedMachineAddFeedDto.getNumber());
        }
        feedMachine.setStartTime(new Date());
        feedMachine.setStopTime(null);
        feedMachine.setOpen(true);
        return feedMachineMapper.updateById(feedMachine) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean shutdown(String machineNumber, String operator, Integer number) {
        QueryWrapper<FeedMachine> wrapper = new QueryWrapper<>();
        wrapper.eq("machine_number",machineNumber);
        FeedMachine feedMachine = feedMachineMapper.selectOne(wrapper);
        if(!feedMachine.getOpen()) {
            return false;
        }
        feedMachine.setStopTime(new Date());
        feedMachine.setOpen(false);

        FeedHistory feedHistory = new FeedHistory();
        feedHistory.setDovecoteNumber(feedMachine.getDovecoteNumber());
        feedHistory.setBaseId(feedMachine.getBaseId());
        feedHistory.setMachineNumber(feedMachine.getMachineNumber());
        feedHistory.setName(feedMachine.getName());
        feedHistory.setType(feedMachine.getType());
        feedHistory.setNumber(feedMachine.getNumber());
        feedHistory.setStartTime(feedMachine.getStartTime());
        feedHistory.setEndTime(feedMachine.getStopTime());
        feedHistory.setOperator(operator);
        feedHistory.setGuige(feedMachine.getGuige());

        feedHistoryMapper.insert(feedHistory);
        feedMachine.setNumber(number);
        if(number == 0){
            feedMachine.setType(null);
        }
        return feedMachineMapper.updateById(feedMachine) > 0;
    }

    @Override
    public List<String> findDeviceName(Long baseId, String dovecoteNumber) {
        return feedHistoryMapper.findDeviceName(baseId, dovecoteNumber);
    }
}
