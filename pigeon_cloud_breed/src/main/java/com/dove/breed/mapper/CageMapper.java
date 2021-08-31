package com.dove.breed.mapper;

import com.dove.breed.entity.Cage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@Mapper
public interface CageMapper extends BaseMapper<Cage> {
    Date getLastOneLayEggsByCageId(@Param("cageId")Long cageId);
    Date getLastTwoLayEggsByCageId(@Param("cageId")Long cageId);
}
