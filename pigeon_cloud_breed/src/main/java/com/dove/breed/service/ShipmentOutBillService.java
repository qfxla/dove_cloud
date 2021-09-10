package com.dove.breed.service;

import com.dove.breed.entity.ShipmentOutBill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基地出库单 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface ShipmentOutBillService extends IService<ShipmentOutBill> {
    List<ShipmentOutBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long baseId);

    int saveBill(ShipmentOutBill shipmentOutBill);
}
