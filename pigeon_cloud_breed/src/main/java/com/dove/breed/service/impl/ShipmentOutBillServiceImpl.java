package com.dove.breed.service.impl;

import com.dove.breed.entity.ShipmentOutBill;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.mapper.ShipmentOutBillMapper;
import com.dove.breed.service.ShipmentOutBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基地出库单 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class ShipmentOutBillServiceImpl extends ServiceImpl<ShipmentOutBillMapper, ShipmentOutBill> implements ShipmentOutBillService {
    @Autowired
    private ShipmentOutBillMapper shipmentOutBillMapper;

    @Override
    public List<ShipmentOutBillVo> findBillByBaseId(Long baseId) {
        return shipmentOutBillMapper.findBillByBaseId(baseId);
    }

    @Override
    public List<ShipmentOutBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long baseId) {
        return shipmentOutBillMapper.findBillByGmt_createAndBaseId(startTime,endTime,baseId);
    }
}
