package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.DoveProcessing;
import com.dove.processing.entity.Vo.DoveProcessingVo;

/**
 * <p>
 * 加工厂表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
public interface DoveProcessingService extends IService<DoveProcessing> {

    Page<DoveProcessingVo> getFactorysByPage(int no, int size);

    Page<DoveProcessingVo> getFactorysByEnterprise(Long enterpriseId,int no, int size);

    Page<DoveProcessingVo> getFatorcysByLike(String value,int no ,int size);

    Page<DoveProcessingVo> getProductByProcessId(Long factoryId,int no,int size);
}
