package com.dove.breed.service;

import com.dove.breed.entity.Drinking;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.DrinkingVo;

import java.util.List;

/**
 * <p>
 * 饮水信息表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
public interface DrinkingService extends IService<Drinking> {

    List<DrinkingVo> listByType(Long baseId, String dovecoteNumber, String operator, String startTime, String endTime, Long enterpriseId);

    List<String> getAllOperator();
}
