package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.ProcessingTechnology;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingStorageVo;
import com.dove.processing.entity.Vo.ProcessingTechnologyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加工工艺表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */

public interface ProcessingTechnologyMapper extends BaseMapper<ProcessingTechnology> {

    List<ProcessingFlowVo> getTechnologyInfoById(@Param("id") Long id, @Param("no") int no, @Param("size") int size);

    List<ProcessingTechnologyVo> getTechnologyInfoByPage(@Param("no") int no , @Param("size") int size);
}
