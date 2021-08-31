package com.dove.breed.service.impl;

import com.dove.breed.entity.ShipmentEntryType;
import com.dove.breed.entity.vo.ShipmentEntryTypeVo;
import com.dove.breed.mapper.ShipmentEntryTypeMapper;
import com.dove.breed.service.ShipmentEntryTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 基地进库类型表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class ShipmentEntryTypeServiceImpl extends ServiceImpl<ShipmentEntryTypeMapper, ShipmentEntryType> implements ShipmentEntryTypeService {

    @Autowired
    private ShipmentEntryTypeMapper shipmentEntryTypeMapper;

    @Override
    public List<ShipmentEntryTypeVo> getTypeNameByType(String type) {
        List<ShipmentEntryTypeVo> typeVoList = shipmentEntryTypeMapper.getTypeNameByType(type);
        return typeVoList;
    }
}
