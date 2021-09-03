package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.ProcessingFlow;
import com.dove.processing.entity.Vo.ProcessingBatchVo;
import com.dove.processing.entity.Vo.ProcessingFlowBindBatchBillVo;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingTechnologyVo;
import com.dove.processing.mapper.ProcessingFlowMapper;
import com.dove.processing.service.ProcessingFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工流程表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Service
public class ProcessingFlowServiceImpl extends ServiceImpl<ProcessingFlowMapper, ProcessingFlow> implements ProcessingFlowService {

    @Resource
    private ProcessingFlowMapper processingFlowMapper;

    @Resource
    private ExecutorService executorService;

    @Override
    public Page<ProcessingFlowVo> getFlowByPage(int no, int size) {
        Future<List<ProcessingFlowVo>> future = executorService.submit(()->processingFlowMapper.getFlowInfoByPage((no-1)*size,size));
        Page<ProcessingFlowVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    @Override
    public Page<ProcessingFlowBindBatchBillVo> getAllProcessInfo(long id, int no, int size) {
        Future<List<ProcessingFlowBindBatchBillVo>> future = executorService.submit(()->processingFlowMapper.getMoreFlowInfo(id,(no-1)*size,size));
        Page<ProcessingFlowBindBatchBillVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    @Override
    public Page<ProcessingFlowVo> getFlowByLikeSearch(String value, int no, int size) {
        Future<List<ProcessingFlowVo>> future = executorService.submit(() -> processingFlowMapper.getFlowInfoByLikeSearch(value, (no - 1) * size, size));
        Page<ProcessingFlowVo> page = DataUtil.getPage(no, size, future);
        return page;
    }

    @Override
    public Page<ProcessingTechnologyVo> getTeachnologyByFlowId(Long id, int no, int size) {
        Future<List<ProcessingTechnologyVo>> future = executorService.submit(()->processingFlowMapper.getTechnologyInfoByFlowId(id,(no-1)*size,size));
        Page<ProcessingTechnologyVo> page = DataUtil.getPage(no,size,future);
        return page;
    }
}
