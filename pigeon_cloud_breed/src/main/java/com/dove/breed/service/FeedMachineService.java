package com.dove.breed.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.FeedMachine;
import com.dove.breed.entity.dto.FeedMachineAddFeedDto;
import com.dove.breed.entity.vo.FeedMachineVo;

import java.util.List;

/**
 * <p>
 * 投喂信息表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
public interface FeedMachineService extends IService<FeedMachine> {

    List<FeedMachineVo> listByType(Long baseId, String dovecoteNumber, Integer open, Long enterpriseId);

    boolean open(String machineNumber, FeedMachineAddFeedDto feedMachineAddFeedDto);

    boolean shutdown(String machineNumber, String operator, Integer number);

    List<String> findDeviceName(Long baseId, String dovecoteNumber);
}
