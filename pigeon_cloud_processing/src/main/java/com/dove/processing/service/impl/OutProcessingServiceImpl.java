package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.OutProcessing;
import com.dove.processing.entity.Vo.DoveProcessingVo;
import com.dove.processing.entity.Vo.OutProcessingVo;
import com.dove.processing.mapper.OutProcessingMapper;
import com.dove.processing.service.OutProcessingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.ConvertUtil;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工厂出库表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Service
public class OutProcessingServiceImpl extends ServiceImpl<OutProcessingMapper, OutProcessing> implements OutProcessingService {

    @Resource
    private OutProcessingMapper outProcessingMapper;

    @Resource
    private ExecutorService executorService;

    @Resource
    private ConvertUtil convertUtil;

    @Override
    public Page<OutProcessingVo> getProcessingInfoByPage(int no, int size) {
        Future<List<OutProcessingVo>> future = executorService.submit(()->outProcessingMapper.getProcessingByPage((no-1)*size,size));
        Page<OutProcessingVo> processingVoPage = DataUtil.getPage(no,size,future);
        return processingVoPage;
    }

    @Override
    public Page<DoveProcessingVo> getFactoryByBossId(Long consignee, int no, int size) {
        Future<List<DoveProcessingVo>> future = executorService.submit(()->outProcessingMapper.getFactoryInfoByBossId(consignee,(no-1)*size,size));
        Page<DoveProcessingVo> doveProcessingVoPage = DataUtil.getPage(no,size,future);
        return doveProcessingVoPage;
    }

    @Override
    public Page<OutProcessingVo> getProcessingByLikeSearch(String value, int no, int size) {
        Future<List<OutProcessingVo>> future = executorService.submit(()->outProcessingMapper.getProcessingByLikeSearch(value,(no-1)*size,size));
        Page<OutProcessingVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    /**
     * 把excel里的东西存进数据库
     * @param list
     */
    @Override
    public void saveList(List<OutProcessingVo> list) {
        for (int i=0;i<list.size();i++){
            OutProcessing convert = convertUtil.convert(list.get(i), OutProcessing.class);
            outProcessingMapper.insert(convert);
        }
    }
}
