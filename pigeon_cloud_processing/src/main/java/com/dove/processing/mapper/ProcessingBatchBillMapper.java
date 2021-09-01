package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.ProcessingBatchBill;
import com.dove.processing.entity.Vo.OutProcessingBothBindVo;
import com.dove.processing.entity.Vo.ProcessingBatchBillVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加工批次信息表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-30
 */

public interface ProcessingBatchBillMapper extends BaseMapper<ProcessingBatchBill> {

    List<ProcessingBatchBillVo> getBatchByPage(@Param("no") int no , @Param("size") int size);
}
