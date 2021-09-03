package com.dove.processing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.Dto.ProcessingBatchBillDto;
import com.dove.processing.entity.Dto.ProcessingBatchDto;
import com.dove.processing.entity.ProcessingBatch;
import com.dove.processing.entity.ProcessingBatchBill;
import com.dove.processing.entity.Vo.ProcessingBatchBindBillVo;
import com.dove.processing.entity.Vo.ProcessingBatchVo;
import com.dove.processing.entity.Vo.ProcessingFlowBindBatchBillVo;
import com.dove.processing.mapper.ProcessingBatchBillMapper;
import com.dove.processing.mapper.ProcessingBatchMapper;
import com.dove.processing.service.ProcessingBatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.processing.utils.ConvertUtil;
import com.dove.processing.utils.DataUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private ProcessingBatchBillMapper processingBatchBillMapper;

    @Resource
    private ExecutorService executorService;

    @Resource
    private ConvertUtil convertUtil;

    @Override
    public Page<ProcessingBatchVo> getBatchInfoByLikeSearch(String value, int no, int size) {
        Future<List<ProcessingBatchVo>> listFuture = executorService.submit(()->processingBatchMapper.getBatchsyLikeSearch(value,(no-1)*size,size));
        Page<ProcessingBatchVo> page = DataUtil.getPage(no,size,listFuture);
        return page;
    }

    @Override
    public Page<ProcessingBatchBindBillVo> getBatchByPage(int no, int size) {
        Future<List<ProcessingBatchBindBillVo>> future = executorService.submit(()->processingBatchMapper.getInfoByPage((no-1)*size,size));
        Page<ProcessingBatchBindBillVo> page = DataUtil.getPage(no,size,future);
        return page;
    }

    /**
     * 添加两表的信息
     * @param processingBatchDto
     * @return
     */
    @Override
    @Transactional
    public boolean saveBindBillInfo(ProcessingBatchDto processingBatchDto) {
        ProcessingBatch processingBatch = convertUtil.convert(processingBatchDto,ProcessingBatch.class);
        Long ID = IdWorker.getId(processingBatch);
        processingBatch.setBatchId(ID);
        int addInfo = processingBatchMapper.insert(processingBatch);
        List<ProcessingBatchBillDto> list = new ArrayList<>();
        int addBillInfo = 0;
        list = processingBatchDto.getProcessingBatchBillDto();
        for (int i = 0; i < list.size(); i++) {
            ProcessingBatchBillDto processingBatchBillDto = list.get(i);
            ProcessingBatchBill processingBatchBill = convertUtil.convert(processingBatchBillDto,ProcessingBatchBill.class);
            processingBatchBill.setBatchId(ID);
            processingBatchBill.setProcessPrincipal(processingBatchDto.getPrincipal());
            processingBatchBill.setProcessTime(processingBatchDto.getProcessingTime());
            addBillInfo = processingBatchBillMapper.insert(processingBatchBill);
        }
        if(addInfo > 0 && addBillInfo > 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 逻辑删除加工信息表的信息
     * @param list
     * @return
     */
    @Override
    public boolean removeBillByIds(ArrayList<Long> list) {
        int number = processingBatchMapper.deleteBatchByProcessId(list);
        if(number > 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查询信息详情
     * @param id
     * @return
     */
    @Override
    public List<ProcessingBatchBindBillVo> getBothInfoByBatchId(long id) {
        List<ProcessingBatchBindBillVo> list = processingBatchMapper.getMoreInfoByBatchId(id);
        return list;
    }

    @Override
    @Transactional
    public boolean updateBindInfo(long id,ProcessingBatchDto processingBatchDto) {
        ProcessingBatch processingBatch = convertUtil.convert(processingBatchDto,ProcessingBatch.class);
        processingBatch.setBatchId(id);
        int num = processingBatchMapper.updateById(processingBatch);
        ProcessingBatchBillDto processingBatchBillDto = processingBatchDto.getProcessingBatchBillDto().get(0);
        ProcessingBatchBill processingBatchBill = convertUtil.convert(processingBatchBillDto,ProcessingBatchBill.class);
        int number = processingBatchBillMapper.update(processingBatchBill,new QueryWrapper<>());
        if(num > 0 && number > 0) {
            return true;
        }else {
            return false;
        }
    }
}
