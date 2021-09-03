package com.dove.breed.mapper;

import com.dove.breed.entity.CageReal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-01
 */
@Mapper
public interface CageRealMapper extends BaseMapper<CageReal> {
    List<CageReal> getAllCage(@Param("baseId")Long baseId,
                              @Param("dovecoteNumber")String dovecoteNumber);
    List<CageReal> getLayEggsTime(@Param("baseId")Long baseId,
                                  @Param("dovecoteNumber")String dovecoteNumber);
    List<CageReal> getHatchTime(@Param("baseId")Long baseId,
                                @Param("dovecoteNumber")String dovecoteNumber);
    List<CageReal> getFeedTime(@Param("baseId")Long baseId,
                               @Param("dovecoteNumber")String dovecoteNumber);
}
