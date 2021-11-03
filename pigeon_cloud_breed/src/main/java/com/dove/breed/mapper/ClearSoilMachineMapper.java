package com.dove.breed.mapper;

import com.dove.breed.entity.ClearSoilMachine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.FeedMachineVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-11-03
 */
@Mapper
public interface ClearSoilMachineMapper extends BaseMapper<ClearSoilMachine> {

    List<FeedMachineVo> listByType(@Param("baseId") Long baseId, @Param("dovecoteNumber") String dovecoteNumber,
                                   @Param("open")Integer open, @Param("enterpriseId") Long enterpriseId);
}
