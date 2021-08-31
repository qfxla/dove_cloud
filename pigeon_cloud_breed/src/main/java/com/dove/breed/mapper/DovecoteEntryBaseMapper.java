package com.dove.breed.mapper;

import com.dove.breed.entity.DovecoteEntryBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.DovecoteEntryBaseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 鸽棚入仓信息表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface DovecoteEntryBaseMapper extends BaseMapper<DovecoteEntryBase> {
    List<DovecoteEntryBaseVo> getFeedEntryOfMonth(@Param("baseId")Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                                  @Param("year")int year, @Param("month")int month);
}
