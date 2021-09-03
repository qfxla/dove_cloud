package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.Dto.OutProcessingBillDto;
import com.dove.processing.entity.Dto.OutProcessingDto;
import com.dove.processing.entity.OutProcessing;
import com.dove.processing.entity.OutProcessingBill;
import com.dove.processing.entity.Vo.*;
import com.dove.processing.mapper.OutProcessingBillMapper;
import com.dove.processing.mapper.OutProcessingMapper;
import com.dove.processing.service.OutProcessingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.ConvertUtil;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private OutProcessingBillMapper outProcessingBillMapper;

    @Resource
    private ExecutorService executorService;

    @Resource
    private ConvertUtil convertUtil;

    @Override
    public Page<OutProcessingBothBindVo> getProcessingInfoByPage(int no, int size) {
        Future<List<OutProcessingBothBindVo>> future = executorService.submit(() -> outProcessingMapper.getProcessingByPage((no - 1) * size, size));
        Page<OutProcessingBothBindVo> processingVoPage = DataUtil.getPage(no, size, future);
        return processingVoPage;
    }

    @Override
    public Page<DoveProcessingVo> getFactoryByBossId(Long consignee, int no, int size) {
        Future<List<DoveProcessingVo>> future = executorService.submit(() -> outProcessingMapper.getFactoryInfoByBossId(consignee, (no - 1) * size, size));
        Page<DoveProcessingVo> doveProcessingVoPage = DataUtil.getPage(no, size, future);
        return doveProcessingVoPage;
    }

    @Override
    public Page<OutProcessingBothBindVo> getProcessingByLikeSearch(String value, int no, int size) {
        Future<List<OutProcessingBothBindVo>> future = executorService.submit(() -> outProcessingMapper.getProcessingByLikeSearch(value, (no - 1) * size, size));
        Page<OutProcessingBothBindVo> page = DataUtil.getPage(no, size, future);
        return page;
    }

    /**
     * 把excel里的东西存进数据库
     *
     * @param list
     */
    @Override
    public void saveList(List<OutProcessInfoVo> list) {
        for (int i = 0; i < list.size(); i++) {
            OutProcessing convert = convertUtil.convert(list.get(i), OutProcessing.class);
            outProcessingMapper.insert(convert);
        }
    }

    /**
     * 绑定两张表,添加信息
     * @param processingDto
     * @return
     */
    @Override
    @Transactional
    public boolean saveBothInfo(OutProcessingDto processingDto) {
        OutProcessing outProcessing = convertUtil.convert(processingDto,OutProcessing.class);
        Long ID= IdWorker.getId(outProcessing);
        outProcessing.setOutId(ID);
        int addInfo = outProcessingMapper.insert(outProcessing);
        List<OutProcessingBillDto> list = new ArrayList<>();
        int addBillInfo = 0;
        list = processingDto.getOutProcessingBillDtos();
        for(int i = 0 ;i < list.size();i++ ) {
            OutProcessingBillDto outProcessingBillDto = list.get(i);
            OutProcessingBill outProcessingBill = convertUtil.convert(outProcessingBillDto,OutProcessingBill.class);
            outProcessingBill.setOutId(ID);
            addBillInfo = outProcessingBillMapper.insert(outProcessingBill);
        }
        if(addInfo > 0 && addBillInfo > 0 ) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 得到出库信息详情
     * @param id
     * @return
     */
    @Override
    public List<OutProcessingBothBindVo> getInfoById(Long id) {
        List<OutProcessingBothBindVo> list = outProcessingMapper.getProcessingById(id);
        return list;
    }

    @Override
    @Transactional
    public boolean updateBillInfo(Long id,OutProcessingDto outProcessingDto) {
        OutProcessingBillDto outProcessingBillDto = outProcessingDto.getOutProcessingBillDtos().get(0);
        OutProcessingBill outProcessingBill = convertUtil.convert(outProcessingBillDto,OutProcessingBill.class);
        int num =outProcessingBillMapper.update(outProcessingBill,new QueryWrapper<OutProcessingBill>().eq("out_id",id));
        if (num > 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查找一个时间段内的数据
     * @param no
     * @param size
     * @param firstTime
     * @param lastTime
     * @return
     */
    @Override
    public Page<OutProcessingBothBindVo> getDataByDateTime(int no, int size, String firstTime, String lastTime) {
        Future<List<OutProcessingBothBindVo>> future = executorService.submit(()->outProcessingMapper.getDataByDateStamp((no-1)*size,size,firstTime,lastTime));
        Page<OutProcessingBothBindVo> page = DataUtil.getPage(no,size,future);
        return page;
    }
}
