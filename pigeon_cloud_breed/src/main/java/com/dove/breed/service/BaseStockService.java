package com.dove.breed.service;

import com.dove.breed.entity.BaseStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基地库存表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-06
 */
public interface BaseStockService extends IService<BaseStock> {
    List<BaseStock> getStockByBaseIdAndType(Long baseId, String type);
}
