package com.dove.breed.mapper;

import com.dove.breed.entity.ShipmentEntryType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.ShipmentEntryTypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 基地进库类型表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface ShipmentEntryTypeMapper extends BaseMapper<ShipmentEntryType> {
    List<ShipmentEntryTypeVo> getTypeNameByType(@Param("type") String type);

    int getMaxProductNumber(@Param("baseId") Long baseId);
}
