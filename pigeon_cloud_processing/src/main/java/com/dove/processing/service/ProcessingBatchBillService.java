package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.ProcessingBatchBill;
import com.dove.processing.entity.Vo.OutProcessingBothBindVo;
import com.dove.processing.entity.Vo.ProcessingBatchBillVo;

/**
 * <p>
 * 加工批次信息表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-30
 */
public interface ProcessingBatchBillService extends IService<ProcessingBatchBill> {

    Page<ProcessingBatchBillVo> getBatchBillInfoByPage(int no, int size);
}
