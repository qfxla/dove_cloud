package com.dove.breed.service;

import com.dove.breed.entity.MonitorBase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.dto.MonitorBaseDto;
import com.dove.breed.entity.vo.MonitorBaseVo;

import java.util.List;

/**
 * <p>
 * 摄像头信息 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
public interface MonitorBaseService extends IService<MonitorBase> {
    boolean add(MonitorBaseDto monitorBaseDto);

    void upData(MonitorBaseDto monitorBaseDto);

    List<MonitorBaseVo> list(Long enterpriseId);

    List<MonitorBaseVo> listByType(Long baseId, Integer type, String dovecoteNumber, Integer statusCode, Long enterpriseId);

    boolean updateToken(Long id);

    List<MonitorBaseVo> getVoById(Long id);
}