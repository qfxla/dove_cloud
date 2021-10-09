package com.dove.breed.service;

import com.dove.breed.entity.ShipmentOutType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.dto.ShipmentOutTypeDto;

/**
 * <p>
 * 基地出库类型表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface ShipmentOutTypeService extends IService<ShipmentOutType> {
    int save(ShipmentOutTypeDto shipmentOutTypeDto);
}
