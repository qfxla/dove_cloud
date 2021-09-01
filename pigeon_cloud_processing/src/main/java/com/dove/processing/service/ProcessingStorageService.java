package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.ProcessingStorage;
import com.dove.processing.entity.Vo.OutProcessingBothBindVo;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingStorageVo;

/**
 * <p>
 * 加工厂库存表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
public interface ProcessingStorageService extends IService<ProcessingStorage> {

    Page<ProcessingStorageVo> getStorageByLikeSearch(String value , int no, int size);

    Page<ProcessingStorageVo> getStorageByPage(int no, int size);
}
