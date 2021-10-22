package com.dove.breed.service.impl;

import com.dove.breed.entity.Drinking;
import com.dove.breed.entity.vo.DrinkingVo;
import com.dove.breed.mapper.DrinkingMapper;
import com.dove.breed.service.DrinkingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 饮水信息表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
@Service
public class DrinkingServiceImpl extends ServiceImpl<DrinkingMapper, Drinking> implements DrinkingService {

    @Resource
    private DrinkingMapper drinkingMapper;

    @Override
    public List<DrinkingVo> listByType(Long baseId, String dovecoteNumber, String operator, String startTime, String endTime, Long enterpriseId) {
        return drinkingMapper.listByType(baseId, dovecoteNumber, operator, startTime, endTime, enterpriseId);
    }

    @Override
    public List<String> getAllOperator() {
        return drinkingMapper.getAllOperator();
    }
}
