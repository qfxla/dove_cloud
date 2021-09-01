package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.ProcessingStorage;
import com.dove.processing.entity.Vo.OutProcessingBothBindVo;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingStorageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加工厂库存表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */

public interface ProcessingStorageMapper extends BaseMapper<ProcessingStorage> {

    List<ProcessingStorageVo> getStorageInfoByLikeSearch(@Param("value") String value, @Param("no") int no, @Param("size") int size);

    List<ProcessingStorageVo> getStorageInfoByPage(@Param("no") int no , @Param("size") int size);
}
