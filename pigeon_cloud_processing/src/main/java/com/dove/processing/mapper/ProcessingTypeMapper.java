package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.ProcessingType;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingTechnologyVo;
import com.dove.processing.entity.Vo.ProcessingTypeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加工产品类型表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */

public interface ProcessingTypeMapper extends BaseMapper<ProcessingType> {

    List<ProcessingFlowVo> getTechnologyInfoById(@Param("id") Long id, @Param("no") int no, @Param("size") int size);

    List<ProcessingTypeVo> getTypeInfoByPage(@Param("no") int no , @Param("size") int size);
}
