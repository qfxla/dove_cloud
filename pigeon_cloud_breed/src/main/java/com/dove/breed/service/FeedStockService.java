package com.dove.breed.service;

import com.dove.breed.entity.FeedStock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.FeedStockVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 月底饲料盘点表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface FeedStockService extends IService<FeedStock> {
    List<FeedStockVo> getFeedAmountOfMonth(String baseName, String dovecoteNumber, int year, int month);
}
