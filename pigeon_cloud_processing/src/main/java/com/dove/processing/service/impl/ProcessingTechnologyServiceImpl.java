package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.ProcessingTechnology;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingStorageVo;
import com.dove.processing.entity.Vo.ProcessingTechnologyVo;
import com.dove.processing.mapper.ProcessingTechnologyMapper;
import com.dove.processing.service.ProcessingTechnologyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工工艺表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Service
public class ProcessingTechnologyServiceImpl extends ServiceImpl<ProcessingTechnologyMapper, ProcessingTechnology> implements ProcessingTechnologyService {

    @Resource
    private ProcessingTechnologyMapper processingTechnologyMapper;

    @Resource
    private ExecutorService executorService;

    @Override
    public Page<ProcessingFlowVo> getFlowInfoByPage(Long id,int no ,int size) {
        Future<List<ProcessingFlowVo>> future = executorService.submit(()->processingTechnologyMapper.getTechnologyInfoById(id,(no-1)*size,size));
        Page<ProcessingFlowVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    @Override
    public Page<ProcessingTechnologyVo> getTechnologyByPage(int no, int size) {
        Future<List<ProcessingTechnologyVo>> future = executorService.submit(()->processingTechnologyMapper.getTechnologyInfoByPage((no-1)*size,size));
        Page<ProcessingTechnologyVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    @Override
    public Page<ProcessingTechnologyVo> getTechnologyByLikeSearch(String value, int no, int size) {
        Future<List<ProcessingTechnologyVo>> future = executorService.submit(() -> processingTechnologyMapper.getTechnologyInfoByLikeSearch(value, (no - 1) * size, size));
        Page<ProcessingTechnologyVo> page = DataUtil.getPage(no, size, future);
        return page;
    }
}
