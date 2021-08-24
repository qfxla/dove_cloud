package com.dove.breed.mapper;

import com.dove.breed.entity.ShipmentEntryBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基地进库单 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface ShipmentEntryBillMapper extends BaseMapper<ShipmentEntryBill> {
    List<ShipmentEntryBillVo> findBillByShipmentId(@Param("shipmentId") Long shipmentId);

    List<ShipmentEntryBillVo> findBillByGmt_createAndShipmentId(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("shipmentId")Long shipmentId);
}
