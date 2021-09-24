package com.dove.breed.service;

import com.dove.breed.entity.MonitorBase;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 摄像头信息 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
public interface MonitorBaseService extends IService<MonitorBase> {
    boolean add(MonitorBase monitorVideo);

    void upData(MonitorBase monitorVideo);

}