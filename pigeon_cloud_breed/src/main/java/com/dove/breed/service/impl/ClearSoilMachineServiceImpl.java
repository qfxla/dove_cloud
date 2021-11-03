package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.ClearSoil;
import com.dove.breed.entity.ClearSoilMachine;
import com.dove.breed.entity.FeedMachine;
import com.dove.breed.entity.vo.FeedMachineVo;
import com.dove.breed.mapper.ClearSoilMachineMapper;
import com.dove.breed.mapper.ClearSoilMapper;
import com.dove.breed.service.ClearSoilMachineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.util.SecurityContextUtil;
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
 * @since 2021-11-03
 */
@Service
public class ClearSoilMachineServiceImpl extends ServiceImpl<ClearSoilMachineMapper, ClearSoilMachine> implements ClearSoilMachineService {

    @Resource
    private ClearSoilMachineMapper clearSoilMachineMapper;

    @Resource
    private ClearSoilMapper clearSoilMapper;

    @Override
    public boolean open(Long id) {
        ClearSoilMachine clearSoilMachine = clearSoilMachineMapper.selectById(id);
        clearSoilMachine.setStartTime(new Date());
        clearSoilMachine.setOpen(true);
        return clearSoilMachineMapper.updateById(clearSoilMachine) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean shutdown(String machineNumber, Integer weight, String type, String operator) {
        QueryWrapper<ClearSoilMachine> wrapper = new QueryWrapper<>();
        wrapper.eq("machine_number",machineNumber);
        ClearSoilMachine clearSoilMachine = clearSoilMachineMapper.selectOne(wrapper);
        clearSoilMachine.setWeight(weight);
        clearSoilMachine.setType(type);
        clearSoilMachine.setStopTime(new Date());
        clearSoilMachine.setOpen(false);

        ClearSoil clearSoil = new ClearSoil();
        clearSoil.setDovecoteNumber(clearSoilMachine.getDovecoteNumber());
        clearSoil.setBaseId(clearSoilMachine.getBaseId());
        clearSoil.setClearTime(clearSoilMachine.getStartTime());
        clearSoil.setWeight(weight);
        clearSoil.setType(type);
        clearSoil.setOperator(operator);
        clearSoil.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        clearSoilMapper.insert(clearSoil);

        return clearSoilMachineMapper.updateById(clearSoilMachine) > 0;
    }

    @Override
    public List<FeedMachineVo> listByType(Long baseId, String dovecoteNumber, Integer open, Long enterpriseId) {
        return clearSoilMachineMapper.listByType(baseId, dovecoteNumber, open, enterpriseId);
    }
}
