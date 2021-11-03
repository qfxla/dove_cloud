package com.dove.breed.mapper;

import com.dove.breed.entity.ClearSoil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.ClearSoilVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 清粪信息表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
public interface ClearSoilMapper extends BaseMapper<ClearSoil> {

    List<ClearSoilVo> listByType(@Param("baseId") Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                 @Param("operator")String operator, @Param("startTime")String startTime,
                                 @Param("endTime")String endTime, @Param("enterpriseId")Long enterpriseId);
}