package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.OutProcessing;
import com.dove.processing.entity.Vo.DoveProcessingVo;
import com.dove.processing.entity.Vo.OutProcessingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加工厂出库表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */

public interface OutProcessingMapper extends BaseMapper<OutProcessing> {


    List<DoveProcessingVo> getFactoryInfoByBossId(@Param("consignee") Long consignee, @Param("no") int no , @Param("size") int size);

    List<OutProcessingVo> getProcessingByPage(@Param("no") int no , @Param("size") int size);

    List<OutProcessingVo> getProcessingByLikeSearch(@Param("value") String value,@Param("no") int no, @Param("size") int size);
}
