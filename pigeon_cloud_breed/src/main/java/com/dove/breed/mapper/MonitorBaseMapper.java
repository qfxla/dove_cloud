package com.dove.breed.mapper;

import com.dove.breed.entity.MonitorBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.MonitorBaseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 摄像头信息 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
public interface MonitorBaseMapper extends BaseMapper<MonitorBase> {

    List<MonitorBaseVo> selectList(@Param("enterpriseId") Long enterpriseId);

    List<MonitorBaseVo> listByType(@Param("baseId") Long baseId, @Param("type") Integer type, @Param("dovecoteNumber") String dovecoteNumber,
                                   @Param("statusCode") Integer statusCode, @Param("enterpriseId") Long enterpriseId);

    List<MonitorBaseVo> getVoById(@Param("id") Long id);
}