package com.dove.breed.service;

import com.dove.breed.entity.ShipmentEntryBill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.dto.ShipmentEntryBaseDto;
import com.dove.breed.entity.dto.ShipmentEntryBillDto;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基地进库单 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface ShipmentEntryBillService extends IService<ShipmentEntryBill> {

    List<ShipmentEntryBillVo> findBillByGmt_createAndShipmentId(Date startTime,Date endTime,Long shipmentId);

    ShipmentEntryBillVo submitShipmentEntryBill(ShipmentEntryBillDto shipmentEntryBillDto,
                                                List<ShipmentEntryBaseDto> shipmentEntryBaseDtoList);
}
