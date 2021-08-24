package com.dove.breed.service;

import com.dove.breed.entity.ShipmentEntryBase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.ShipmentEntryBaseVo;

import java.util.List;

/**
 * <p>
 * 基地进库信息表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface ShipmentEntryBaseService extends IService<ShipmentEntryBase> {
    List<ShipmentEntryBaseVo>  findBaseByBill(Long billId);
}
