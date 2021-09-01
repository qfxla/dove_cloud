package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.ProcessingBatch;
import com.dove.processing.entity.Vo.ProcessingBatchVo;
import com.dove.processing.mapper.ProcessingBatchMapper;
import com.dove.processing.service.ProcessingBatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工批次表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Service
public class ProcessingBatchServiceImpl extends ServiceImpl<ProcessingBatchMapper, ProcessingBatch> implements ProcessingBatchService {

    @Resource
    private ProcessingBatchMapper processingBatchMapper;

    @Resource
    private ExecutorService executorService;

    @Override
    public Page<ProcessingBatchVo> getBatchInfoByLikeSearch(String value, int no, int size) {
        Future<List<ProcessingBatchVo>> listFuture = executorService.submit(()->processingBatchMapper.getBatchsyLikeSearch(value,(no-1)*size,size));
        Page<ProcessingBatchVo> page = DataUtil.getPage(no,size,listFuture);
        return page;
    }

    @Override
    public Page<ProcessingBatchVo> getBatchByPage(int no, int size) {
        Future<List<ProcessingBatchVo>> future = executorService.submit(()->processingBatchMapper.getInfoByPage((no-1)*size,size));
        Page<ProcessingBatchVo> page = DataUtil.getPage(no,size,future);
        return page;
    }
}
