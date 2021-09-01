package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.ProcessingFlow;
import com.dove.processing.entity.Vo.ProcessingBatchBillVo;
import com.dove.processing.entity.Vo.ProcessingBatchVo;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加工流程表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */

public interface ProcessingFlowMapper extends BaseMapper<ProcessingFlow> {

    List<ProcessingFlowVo> getFlowInfoByPage(@Param("no") int no , @Param("size") int size);
}
