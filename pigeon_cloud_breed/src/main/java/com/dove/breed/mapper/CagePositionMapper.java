package com.dove.breed.mapper;

import com.dove.breed.entity.CagePosition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@Mapper
public interface CagePositionMapper extends BaseMapper<CagePosition> {
    Long getCageId(@Param("baseId")Long baseId,
                   @Param("dovecoteNumber")String dovecoteNumber,
                   @Param("rowNo")int row,
                   @Param("line")int line,
                   @Param("columnNo")int column);
}
