package com.dove.breed.mapper;

import com.dove.breed.entity.Drinking;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.DrinkingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 饮水信息表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
public interface DrinkingMapper extends BaseMapper<Drinking> {

    List<DrinkingVo> listByType(@Param("baseId") Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                @Param("operator")String operator, @Param("startTime")String startTime,
                                @Param("endTime")String endTime, @Param("enterpriseId")Long enterpriseId);

    List<String> getAllOperator();
}