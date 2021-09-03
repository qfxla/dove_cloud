package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.BusinessProcessing;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.ProcessingTypeVo;

/**
 * <p>
 * 商家表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
public interface BusinessProcessingService extends IService<BusinessProcessing> {

    Page<BusinessProcessingVo> getBusinesssByPage(int no, int size);

    Page<BusinessProcessingVo> getBusinessByBossName(String value,int no,int size);

    Page<ProcessingTypeVo> getProductInfoById(Long id,int no,int size);
}
