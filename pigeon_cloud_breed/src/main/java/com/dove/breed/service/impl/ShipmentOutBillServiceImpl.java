package com.dove.breed.service.impl;

import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.ShipmentOutBill;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.mapper.DovecoteOutBillMapper;
import com.dove.breed.mapper.ShipmentOutBillMapper;
import com.dove.breed.service.DovecoteOutBillService;
import com.dove.breed.service.ShipmentOutBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.GetMonth;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
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
    @Autowired
    private DovecoteOutBillService dovecoteOutBillService;

    @Override
    public List<ShipmentOutBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long baseId) {
        return shipmentOutBillMapper.findBillByGmt_createAndBaseId(startTime,endTime,baseId);
    }

    @Override
    public int saveBill(ShipmentOutBill shipmentOutBill) {
        //根据日期生成批次号
        String farmBatch = GetMonth.getDateToString();
        shipmentOutBill.setFarmBatch(farmBatch);
        //把今天鸽棚同种类型的出库单填上该批次号和算总数量
        int totalAmount = 0;
        List<DovecoteOutBill> dovecoteOutBillList = dovecoteOutBillService.findDovecoteOutBillByTodayAndType(shipmentOutBill.getBaseId(),shipmentOutBill.getType());
        for (DovecoteOutBill dovecoteOutBill : dovecoteOutBillList) {
            dovecoteOutBill.setFarmBatch(farmBatch);
            totalAmount += dovecoteOutBill.getAmount();
        }
        boolean b = dovecoteOutBillService.updateBatchById(dovecoteOutBillList);
        if (!b){
            throw new GlobalException(StatusCode.ERROR,"鸽棚批次号添加失败");
        }
        shipmentOutBill.setAmount(totalAmount);
        int insert = shipmentOutBillMapper.insert(shipmentOutBill);
        return insert;
    }
}
