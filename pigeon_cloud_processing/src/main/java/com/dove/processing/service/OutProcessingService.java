package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.OutProcessing;
import com.dove.processing.entity.Vo.DoveProcessingVo;
import com.dove.processing.entity.Vo.OutProcessingVo;

import java.util.List;

/**
 * <p>
 * 加工厂出库表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
public interface OutProcessingService extends IService<OutProcessing> {

    Page<DoveProcessingVo> getFactoryByBossId(Long consignee, int no , int size);

    Page<OutProcessingVo> getProcessingInfoByPage(int no, int size);

    Page<OutProcessingVo> getProcessingByLikeSearch(String value ,int no,int size);

    void saveList(List<OutProcessingVo> list);
}
