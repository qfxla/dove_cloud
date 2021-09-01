package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.entity.InProcessingBill;
import com.dove.processing.entity.Vo.BillBindInfoVo;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.InProcessingBothVo;
import com.dove.processing.mapper.InProcessingBillMapper;
import com.dove.processing.service.InProcessingBillService;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工厂入库单表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Service
public class InProcessingBillServiceImpl extends ServiceImpl<InProcessingBillMapper, InProcessingBill> implements InProcessingBillService {

    @Resource
    private ExecutorService executorService;

    @Resource
    private InProcessingBillMapper inProcessingBillMapper;

    @Override
    public List<InProcessingBothVo> getInPrcessingInfosByNoPage() {
        Future<List<InProcessingBothVo>> future = null;
        List<InProcessingBothVo> list = new ArrayList<>();
        try {
            future =  executorService.submit(()->inProcessingBillMapper.getInBillsByNoPage());
            list = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Page<BusinessProcessingVo> getBusInfoBySource(Long source, int no, int size) {
        Future<List<BusinessProcessingVo>> future = executorService.submit(()->inProcessingBillMapper.getBusInfosBySource(source,(no-1)*size,size));
        Page<BusinessProcessingVo> page = DataUtil.getPage(no,size,future);
        return page;
    }
}
