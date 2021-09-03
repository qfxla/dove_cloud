package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.entity.ProcessingStorage;
import com.dove.processing.entity.Vo.OutProcessingBothBindVo;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingStorageVo;
import com.dove.processing.mapper.ProcessingStorageMapper;
import com.dove.processing.service.ProcessingStorageService;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工厂库存表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Service
public class ProcessingStorageServiceImpl extends ServiceImpl<ProcessingStorageMapper, ProcessingStorage> implements ProcessingStorageService {

    @Resource
    private ProcessingStorageMapper processingStorageMapper;

    @Resource
    private ExecutorService executorService;

    @Override
    public Page<ProcessingStorageVo> getStorageByLikeSearch(String value, int no, int size) {
        Future<List<ProcessingStorageVo>> future = executorService.submit(() -> processingStorageMapper.getStorageInfoByLikeSearch(value, (no - 1) * size, size));
        Page<ProcessingStorageVo> page = DataUtil.getPage(no, size, future);
        return page;
    }

    @Override
    public Page<ProcessingStorageVo> getStorageByPage(int no, int size) {
        Future<List<ProcessingStorageVo>> future = executorService.submit(()->processingStorageMapper.getStorageInfoByPage((no-1)*size,size));
        Page<ProcessingStorageVo> page = DataUtil.getPage(no,size,future);
        return page;
    }
}
