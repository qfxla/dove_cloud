package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.DoveProcessing;
import com.dove.processing.entity.Vo.DoveProcessingVo;
import com.dove.processing.mapper.DoveProcessingMapper;
import com.dove.processing.service.DoveProcessingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工厂表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Service
public class DoveProcessingServiceImpl extends ServiceImpl<DoveProcessingMapper, DoveProcessing> implements DoveProcessingService {

    @Resource
    private DoveProcessingMapper doveProcessingMapper;

    @Resource
    private ExecutorService executorService;

    @Override
    public Page<DoveProcessingVo> getFactorysByPage(int no, int size) {
        Future<List<DoveProcessingVo>> getDoveProcessingVoByPage = executorService.submit(()->doveProcessingMapper.getDoveProcessiInfo((no -1)*size,size));
        Page<DoveProcessingVo> doveProcessingVoPage = DataUtil.getPage(no,size,getDoveProcessingVoByPage);
        return doveProcessingVoPage;

    }

    @Override
    public Page<DoveProcessingVo> getFactorysByEnterprise(Long enterpriseId, int no, int size) {
        Future<List<DoveProcessingVo>> getDoveProcessingsVoByPage = executorService.submit(()->doveProcessingMapper.getFactorysByEnterpriseId(enterpriseId,(no-1)*size,size));
        Page<DoveProcessingVo> doveProcessingsVoPage = DataUtil.getPage(no,size,getDoveProcessingsVoByPage);
        return doveProcessingsVoPage;
    }

    @Override
    public Page<DoveProcessingVo> getFatorcysByLike(String value, int no, int size) {
        Future<List<DoveProcessingVo>> getDoveProcessingsVoByPage = executorService.submit(()->doveProcessingMapper.getFactoryByLikeSearch(value,(no-1)*size,size));
        Page<DoveProcessingVo> processingVoPage = DataUtil.getPage(no,size,getDoveProcessingsVoByPage);
        return processingVoPage;
    }

    @Override
    public Page<DoveProcessingVo> getProductByProcessId(Long factoryId, int no, int size) {
        Future<List<DoveProcessingVo>> future = executorService.submit(()->doveProcessingMapper.getProductInfoByProcessId(factoryId,(no-1)*size,size));
        Page<DoveProcessingVo> doveVoPage = DataUtil.getPage(no,size,future);
        return doveVoPage;
    }
}
