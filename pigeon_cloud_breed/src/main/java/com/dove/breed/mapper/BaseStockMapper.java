package com.dove.breed.mapper;

import com.dove.breed.entity.BaseStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 基地库存表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-06
 */
@Mapper
public interface BaseStockMapper extends BaseMapper<BaseStock> {
    List<BaseStock> getStockByBaseIdAndType(@Param("baseId")Long baseId,
                                            @Param("type")String type);

    List<BaseStock> fuzzyquery(@Param("name")String name);
}
