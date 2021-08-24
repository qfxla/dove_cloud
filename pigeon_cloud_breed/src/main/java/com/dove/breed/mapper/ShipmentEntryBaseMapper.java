package com.dove.breed.mapper;

import com.dove.breed.entity.ShipmentEntryBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.ShipmentEntryBaseVo;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 基地进库信息表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface ShipmentEntryBaseMapper extends BaseMapper<ShipmentEntryBase> {
    List<ShipmentEntryBaseVo> findBaseByBill(@Param("billId")Long billId);
}
