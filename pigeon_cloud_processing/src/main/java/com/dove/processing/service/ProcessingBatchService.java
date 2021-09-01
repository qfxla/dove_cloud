package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.ProcessingBatch;
import com.dove.processing.entity.Vo.ProcessingBatchVo;

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

    Page<ProcessingBatchVo> getBatchByPage(int no, int size);
}
