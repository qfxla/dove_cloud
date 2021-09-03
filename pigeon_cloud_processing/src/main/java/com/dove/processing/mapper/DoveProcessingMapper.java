package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.DoveProcessing;
import com.dove.processing.entity.Vo.DoveProcessingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加工厂表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */

public interface DoveProcessingMapper extends BaseMapper<DoveProcessing> {

    List<DoveProcessingVo> getDoveProcessiInfo(@Param("no") int no, @Param("size") int size);

    List<DoveProcessingVo> getFactorysByEnterpriseId(@Param("enterpriseId") Long enterpriseId,@Param("no") int no, @Param("size") int size);

    List<DoveProcessingVo> getFactoryByLikeSearch(@Param("value") String value,@Param("no") int no, @Param("size") int size);

    List<DoveProcessingVo> getProductInfoByProcessId(@Param("factoryId") Long factoryId,@Param("no") int no, @Param("size") int size);
}
