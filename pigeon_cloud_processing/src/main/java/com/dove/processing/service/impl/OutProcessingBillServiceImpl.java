package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.OutProcessingBill;
import com.dove.processing.entity.Vo.BillBindInfoVo;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.OutProcessingBillVo;
import com.dove.processing.mapper.OutProcessingBillMapper;
import com.dove.processing.service.OutProcessingBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.ConvertUtil;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 加工厂出库单表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Service
public class OutProcessingBillServiceImpl extends ServiceImpl<OutProcessingBillMapper, OutProcessingBill> implements OutProcessingBillService {

    @Resource
    private OutProcessingBillMapper outProcessingBillMapper;

    @Resource
    private ExecutorService executorService;

    @Resource
    ConvertUtil convertUtil;

    @Override
    public Page<OutProcessingBillVo> getBillInfoByPage(int no, int size) {
        Future<List<OutProcessingBillVo>> future = executorService.submit(()->outProcessingBillMapper.getBillByPage((no-1)*size,size));
        Page<OutProcessingBillVo> outProcessingBillVoPage = DataUtil.getPage(no,size,future);
        return outProcessingBillVoPage;
    }

    @Override
    public Page<BusinessProcessingVo> getBusInfoByBossId(Long consignee, int no, int size) {
        Future<List<BusinessProcessingVo>> future = executorService.submit(()->outProcessingBillMapper.getBusInfoByBossId(consignee,(no-1)*size,size));
        Page<BusinessProcessingVo> processingVoPage = DataUtil.getPage(no,size,future);
        return processingVoPage;
    }

    @Override
    public Page<OutProcessingBillVo> getBillByLikeSearch(String value, int no, int size) {
        Future<List<OutProcessingBillVo>> future = executorService.submit(()->outProcessingBillMapper.getBillsByLikeSearch(value,(no-1)*size,size));
        Page<OutProcessingBillVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    /**
     * 读取excel文件的内容存进数据库
     * @param list
     */
    @Override
    public void saveList(List<OutProcessingBillVo> list) {
        for (int i=0;i<list.size();i++){
            OutProcessingBill convert = convertUtil.convert(list.get(i), OutProcessingBill.class);
            outProcessingBillMapper.insert(convert);
        }
    }

    @Override
    public List<BillBindInfoVo> getBillInfosByNoPage() {
        Future<List<BillBindInfoVo>> future = null;
        List<BillBindInfoVo> list = new ArrayList<>();
        try {
            future =  executorService.submit(()->outProcessingBillMapper.getBillsByNoPage());
            list = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
