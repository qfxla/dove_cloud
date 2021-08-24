package com.dove.breed.mapper;

import com.dove.breed.entity.ShipmentOutBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.ShipmentOutBaseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 基地出库信息表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface ShipmentOutBaseMapper extends BaseMapper<ShipmentOutBase> {
    List<ShipmentOutBaseVo> findBaseByFarmBatch(@Param("farmBatch")Long farmBatch);
}
