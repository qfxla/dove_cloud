package com.dove.breed.service.impl;

import com.dove.breed.entity.ShipmentEntryBill;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;
import com.dove.breed.mapper.ShipmentEntryBillMapper;
import com.dove.breed.service.ShipmentEntryBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基地进库单 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class ShipmentEntryBillServiceImpl extends ServiceImpl<ShipmentEntryBillMapper, ShipmentEntryBill> implements ShipmentEntryBillService {

    @Autowired
    ShipmentEntryBillMapper shipmentEntryBillMapper;

    @Override
    public List<ShipmentEntryBillVo> findBillByShipmentId(Long shipmentId) {
        return shipmentEntryBillMapper.findBillByShipmentId(shipmentId);
    }

    @Override
    public List<ShipmentEntryBillVo> findBillByGmt_createAndShipmentId(Date startTime, Date endTime,Long shipmentId) {
        return shipmentEntryBillMapper.findBillByGmt_createAndShipmentId(startTime,endTime,shipmentId);
    }

}
