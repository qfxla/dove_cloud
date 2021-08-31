package com.dove.breed.service;

import com.dove.breed.entity.ShipmentEntryType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.ShipmentEntryTypeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 基地进库类型表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface ShipmentEntryTypeService extends IService<ShipmentEntryType> {
    List<ShipmentEntryTypeVo> getTypeNameByType(String type);
}
