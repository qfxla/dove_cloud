package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.ProcessingFlow;
import com.dove.processing.entity.Vo.ProcessingBatchVo;
import com.dove.processing.entity.Vo.ProcessingFlowBindBatchBillVo;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingTechnologyVo;

/**
 * <p>
 * 加工流程表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
public interface ProcessingFlowService extends IService<ProcessingFlow> {

    Page<ProcessingFlowVo> getFlowByPage(int no, int size);

    Page<ProcessingFlowBindBatchBillVo> getAllProcessInfo(long id ,int no ,int size);

    Page<ProcessingFlowVo> getFlowByLikeSearch(String value , int no, int size);

    Page<ProcessingTechnologyVo> getTeachnologyByFlowId(Long id ,int no ,int size );
}
