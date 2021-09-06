package com.dove.breed.mapper;

import com.dove.breed.entity.ManualIncubation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * <p>
 * 人工孵化表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-04
 */
@Mapper
public interface ManualIncubationMapper extends BaseMapper<ManualIncubation> {
    //查有无今天数据
    List<ManualIncubation> getTodayData(@Param("baseId") Long baseId,
                                        @Param("dovecoteNumber") String dovecoteNumber);
    //查某个鸽棚的数据
    List<ManualIncubation> getByDovecoteNumber(@Param("baseId")Long baseId,
                                               @Param("dovecoteNumber")String dovecoteNumber);
    //根据日期查数据
    List<ManualIncubation> getByDate(@Param("baseId")Long baseId,@Param("year")int year,
                                     @Param("month")int month,@Param("day")int day);
}
