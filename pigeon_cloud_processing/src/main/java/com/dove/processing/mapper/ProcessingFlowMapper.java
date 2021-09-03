package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.ProcessingFlow;
import com.dove.processing.entity.Vo.*;
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

    List<ProcessingFlowBindBatchBillVo> getMoreFlowInfo(@Param("id") long id,@Param("no") int no , @Param("size") int size);

    List<ProcessingFlowVo> getFlowInfoByLikeSearch(@Param("value") String value, @Param("no") int no, @Param("size") int size);

    List<ProcessingTechnologyVo> getTechnologyInfoByFlowId(@Param("id") Long id,@Param("no") int no, @Param("size") int size);
}

