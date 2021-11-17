package com.dove.breed.mapper;

import com.dove.breed.entity.DrinkingMachine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.DrinkingMachineVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-11-16
 */
public interface DrinkingMachineMapper extends BaseMapper<DrinkingMachine> {

    List<DrinkingMachineVo> listByType(@Param("baseId") Long baseId,
                                       @Param("dovecoteNumber") String dovecoteNumber,
                                       @Param("open") Integer open, @Param("enterpriseId") Long enterpriseId);
}
