package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.entity.ProcessingBatchBill;
import com.dove.processing.entity.Vo.OutProcessingBothBindVo;
import com.dove.processing.entity.Vo.ProcessingBatchBillVo;
import com.dove.processing.mapper.ProcessingBatchBillMapper;
import com.dove.processing.service.ProcessingBatchBillService;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工批次信息表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-30
 */
@Service
public class ProcessingBatchBillServiceImpl extends ServiceImpl<ProcessingBatchBillMapper, ProcessingBatchBill> implements ProcessingBatchBillService {

    @Resource
    private ProcessingBatchBillMapper processingBatchBillMapper;

    @Resource
    private ExecutorService executorService;

    @Override
    public Page<ProcessingBatchBillVo> getBatchBillInfoByPage(int no, int size) {
        Future<List<ProcessingBatchBillVo>> future = executorService.submit(() -> processingBatchBillMapper.getBatchByPage((no - 1) * size, size));
        Page<ProcessingBatchBillVo> page = DataUtil.getPage(no, size, future);
        return page;
    }
}
