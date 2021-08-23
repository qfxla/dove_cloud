package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.BusinessProcessing;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.ProcessingTypeVo;
import com.dove.processing.mapper.BusinessProcessingMapper;
import com.dove.processing.service.BusinessProcessingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 商家表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Service
public class BusinessProcessingServiceImpl extends ServiceImpl<BusinessProcessingMapper, BusinessProcessing> implements BusinessProcessingService {

    @Resource
    private BusinessProcessingMapper businessProcessingMapper;

    @Resource
    private ExecutorService executorService;

    @Override
    public Page<BusinessProcessingVo> getBusinesssByPage(int no, int size) {
        Future<List<BusinessProcessingVo>> getBusinessProcessingVoByPage = executorService.submit(()->businessProcessingMapper.getBusiInfo((no-1)*size,size));
        Page<BusinessProcessingVo> businessProcessingVoPage = DataUtil.getPage(no,size,getBusinessProcessingVoByPage);
        return businessProcessingVoPage;
    }

    @Override
    public Page<BusinessProcessingVo> getBusinessByBossName(String value, int no, int size) {
        Future<List<BusinessProcessingVo>> listFuture = executorService.submit(()->businessProcessingMapper.getBusiByLikeSearch(value,(no-1)*size,size));
        long total = businessProcessingMapper.getBusinessProcessingCountByLike(value);
        Page<BusinessProcessingVo> businessProcessingVoByLikePage = DataUtil.getPage(no,size,total,listFuture);
        return businessProcessingVoByLikePage;
    }

    @Override
    public Page<ProcessingTypeVo> getProductInfoById(Long id,int no, int size) {
        Future<List<ProcessingTypeVo>> future = executorService.submit(()->businessProcessingMapper.getProductsByPage(id,(no-1)*size,size));
        Page<ProcessingTypeVo> processingTypeVoPage = DataUtil.getPage(no,size,future);
        return processingTypeVoPage;
    }
}
