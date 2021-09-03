package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.ProcessingType;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingTechnologyVo;
import com.dove.processing.entity.Vo.ProcessingTypeVo;

/**
 * <p>
 * 加工产品类型表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
public interface ProcessingTypeService extends IService<ProcessingType> {

    Page<ProcessingFlowVo> getFlowInfoByPage(Long id , int no , int size);

    Page<ProcessingTypeVo> getTypeByPage(int no, int size);
}
