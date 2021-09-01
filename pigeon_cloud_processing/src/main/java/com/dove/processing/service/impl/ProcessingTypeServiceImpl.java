package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.ProcessingType;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingTechnologyVo;
import com.dove.processing.entity.Vo.ProcessingTypeVo;
import com.dove.processing.mapper.ProcessingTypeMapper;
import com.dove.processing.service.ProcessingTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工产品类型表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Service
public class ProcessingTypeServiceImpl extends ServiceImpl<ProcessingTypeMapper, ProcessingType> implements ProcessingTypeService {

    @Resource
    private ProcessingTypeMapper processingTypeMapper;

    @Resource
    private ExecutorService executorService;


    @Override
    public Page<ProcessingFlowVo> getFlowInfoByPage(Long id, int no, int size) {
        Future<List<ProcessingFlowVo>> future = executorService.submit(()->processingTypeMapper.getTechnologyInfoById(id,(no-1)*size,size));
        Page<ProcessingFlowVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    @Override
    public Page<ProcessingTypeVo> getTypeByPage(int no, int size) {
        Future<List<ProcessingTypeVo>> future = executorService.submit(()->processingTypeMapper.getTypeInfoByPage((no-1)*size,size));
        Page<ProcessingTypeVo> page = DataUtil.getPage(no,size,future);
        return page;
    }
}
