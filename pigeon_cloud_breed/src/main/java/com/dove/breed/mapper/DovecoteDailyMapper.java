package com.dove.breed.mapper;

import com.dove.breed.entity.DovecoteDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 鸽棚日结表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-02
 */
@Mapper
public interface DovecoteDailyMapper extends BaseMapper<DovecoteDaily> {
    Long getMatEggsOfAmount(@Param("baseId")Long baseId,
                            @Param("dovecoteNumber")String dovecoteNumber);
}
