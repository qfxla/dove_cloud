package com.dove.breed.mapper;

import com.dove.breed.entity.BreedBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 养殖基地信息表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface BreedBaseMapper extends BaseMapper<BreedBase> {
    //查某个基地绩效最高的鸽棚编号
    String getTheBestDovecote(@Param("baseId")Long baseId);
}
