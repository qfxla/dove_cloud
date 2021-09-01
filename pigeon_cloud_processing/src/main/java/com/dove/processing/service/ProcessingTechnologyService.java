package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.ProcessingTechnology;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingStorageVo;
import com.dove.processing.entity.Vo.ProcessingTechnologyVo;

/**
 * <p>
 * 加工工艺表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
public interface ProcessingTechnologyService extends IService<ProcessingTechnology> {

    Page<ProcessingFlowVo> getFlowInfoByPage(Long id ,int no ,int size);

    Page<ProcessingTechnologyVo> getTechnologyByPage(int no, int size);
}
