package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.BaseStock;
import com.dove.breed.entity.DovecoteOutBase;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.mapper.BaseStockMapper;
import com.dove.breed.service.BaseStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基地库存表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-06
 */
@Service
public class BaseStockServiceImpl extends ServiceImpl<BaseStockMapper, BaseStock> implements BaseStockService {
    @Autowired
    private BaseStockMapper baseStockMapper;

    @Override
    public List<BaseStock>  getStockByBaseIdAndType(Long baseId, String type) {
        List<BaseStock> baseStockList = baseStockMapper.getStockByBaseIdAndType(baseId, type);
        return baseStockList;
    }
}
