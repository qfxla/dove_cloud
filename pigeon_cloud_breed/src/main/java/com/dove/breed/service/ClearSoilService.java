package com.dove.breed.service;

import com.dove.breed.entity.ClearSoil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.ClearSoilVo;

import java.util.List;

/**
 * <p>
 * 清粪信息表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
public interface ClearSoilService extends IService<ClearSoil> {

    List<ClearSoilVo> listByType(Long baseId, String dovecoteNumber, String operator, String startTime, String endTime, Long enterpriseId);

    List<String> getAllOperator();
}