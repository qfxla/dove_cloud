package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.Dto.ProcessingBatchDto;
import com.dove.processing.entity.ProcessingBatch;
import com.dove.processing.entity.Vo.ProcessingBatchBindBillVo;
import com.dove.processing.entity.Vo.ProcessingBatchVo;
import com.dove.processing.entity.Vo.ProcessingFlowBindBatchBillVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 加工批次表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
public interface ProcessingBatchService extends IService<ProcessingBatch> {

    Page<ProcessingBatchVo>  getBatchInfoByLikeSearch(String value , int no, int size);

    Page<ProcessingBatchBindBillVo> getBatchByPage(int no, int size);

    boolean saveBindBillInfo(ProcessingBatchDto processingBatchDto);

    boolean removeBillByIds(ArrayList<Long> list);

    List<ProcessingBatchBindBillVo> getBothInfoByBatchId(long id );

    boolean updateBindInfo( long id,ProcessingBatchDto processingBatchDto);
}
