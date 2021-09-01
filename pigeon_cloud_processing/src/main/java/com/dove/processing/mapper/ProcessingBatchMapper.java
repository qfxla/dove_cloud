package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.ProcessingBatch;
import com.dove.processing.entity.Vo.ProcessingBatchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加工批次表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */

public interface ProcessingBatchMapper extends BaseMapper<ProcessingBatch> {

    List<ProcessingBatchVo> getBatchsyLikeSearch(@Param("value") String value, @Param("no") int no, @Param("size") int size);

    List<ProcessingBatchVo> getInfoByPage(@Param("no") int no, @Param("size") int size);
}
