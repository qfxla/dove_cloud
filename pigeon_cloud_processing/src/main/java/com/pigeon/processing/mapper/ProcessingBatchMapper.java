package com.pigeon.processing.mapper;

import com.pigeon.processing.entity.ProcessingBatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 加工批次表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Mapper
public interface ProcessingBatchMapper extends BaseMapper<ProcessingBatch> {

}
