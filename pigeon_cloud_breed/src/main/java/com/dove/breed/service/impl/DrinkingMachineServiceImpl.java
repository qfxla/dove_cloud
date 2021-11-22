package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.Drinking;
import com.dove.breed.entity.DrinkingMachine;
import com.dove.breed.entity.FeedMachine;
import com.dove.breed.entity.vo.DrinkingMachineVo;
import com.dove.breed.entity.vo.DrinkingVo;
import com.dove.breed.mapper.DrinkingMachineMapper;
import com.dove.breed.mapper.DrinkingMapper;
import com.dove.breed.service.DrinkingMachineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-11-16
 */
@Service
public class DrinkingMachineServiceImpl extends ServiceImpl<DrinkingMachineMapper, DrinkingMachine> implements DrinkingMachineService {

    @Resource
    private DrinkingMachineMapper drinkingMachineMapper;

    @Resource
    private DrinkingMapper drinkingMapper;

    @Override
    public List<DrinkingMachineVo> listByType(Long baseId, String dovecoteNumber, Integer open, Long enterpriseId) {
        return drinkingMachineMapper.listByType(baseId, dovecoteNumber, open, enterpriseId);
    }

    @Override
    public boolean open(String machineNumber) {
        QueryWrapper<DrinkingMachine> wrapper = new QueryWrapper<>();
        wrapper.eq("machine_number",machineNumber);
        DrinkingMachine drinkingMachine = drinkingMachineMapper.selectOne(wrapper);
        if(drinkingMachine == null || drinkingMachine.getOpen()) {
            return false;
        }
        drinkingMachine.setOpen(true);
        drinkingMachine.setStartTime(new Date());
        drinkingMachine.setStopTime(null);
        return drinkingMachineMapper.updateById(drinkingMachine) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean shutdown(String machineNumber, String operator) {
        QueryWrapper<DrinkingMachine> wrapper = new QueryWrapper<>();
        wrapper.eq("machine_number",machineNumber);
        DrinkingMachine drinkingMachine = drinkingMachineMapper.selectOne(wrapper);
        if(!drinkingMachine.getOpen()) {
            return false;
        }
        drinkingMachine.setStopTime(new Date());
        drinkingMachine.setOpen(false);
        drinkingMachineMapper.updateById(drinkingMachine);
        Drinking drinking = new Drinking();
        drinking.setDovecoteNumber(drinkingMachine.getDovecoteNumber());
        drinking.setBaseId(drinkingMachine.getBaseId());
        drinking.setMachineNumber(machineNumber);
        drinking.setStartTime(drinkingMachine.getStartTime());
        drinking.setEndTime(drinkingMachine.getStopTime());
        drinking.setOperator(operator);
        drinking.setGuige(drinkingMachine.getGuige());
        return drinkingMapper.insert(drinking) > 0;
    }
}
