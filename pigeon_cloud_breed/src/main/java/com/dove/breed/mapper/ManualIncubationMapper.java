package com.dove.breed.mapper;

import com.dove.breed.entity.ManualIncubation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 孵化机 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-09
 */
@Mapper
public interface ManualIncubationMapper extends BaseMapper<ManualIncubation> {
    //查有无今天数据
    List<ManualIncubation> getTodayData(@Param("baseId") Long baseId,
                                        @Param("dovecoteNumber") String dovecoteNumber);

    List<ManualIncubation> getByDovecoteNumber(@Param("baseId") Long baseId,
                                               @Param("dovecoteNumber") String dovecoteNumber);

    List<ManualIncubation> getByDate(Long baseId, int year, int month, int day);
}
