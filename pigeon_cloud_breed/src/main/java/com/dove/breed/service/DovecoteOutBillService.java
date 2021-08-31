package com.dove.breed.service;

import com.dove.breed.entity.DovecoteOutBill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 鸽棚出仓单 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface DovecoteOutBillService extends IService<DovecoteOutBill> {
    List<DovecoteOutBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long dovecoteId);
}
