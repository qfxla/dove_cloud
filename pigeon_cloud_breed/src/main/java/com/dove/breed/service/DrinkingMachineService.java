package com.dove.breed.service;

import com.dove.breed.entity.DrinkingMachine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.DrinkingMachineVo;
import com.dove.breed.entity.vo.DrinkingVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zcj
 * @since 2021-11-16
 */
public interface DrinkingMachineService extends IService<DrinkingMachine> {

    List<DrinkingMachineVo> listByType(Long baseId, String dovecoteNumber, Integer open, Long enterpriseId);

    boolean open(String machineNumber);

    boolean shutdown(String machineNumber, String operator);
}
