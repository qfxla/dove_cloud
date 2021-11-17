package com.dove.breed.service;

import com.dove.breed.entity.ClearSoilMachine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.FeedMachineVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zcj
 * @since 2021-11-03
 */
public interface ClearSoilMachineService extends IService<ClearSoilMachine> {

    boolean open(String machineNumber);

    boolean shutdown(String machineNumber, Integer weight, String type, String operator);

    List<FeedMachineVo> listByType(Long baseId, String dovecoteNumber, Integer open, Long enterpriseId);
}
