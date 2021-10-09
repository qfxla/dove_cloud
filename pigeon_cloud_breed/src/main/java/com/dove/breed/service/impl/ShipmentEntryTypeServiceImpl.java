package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.ShipmentEntryType;
import com.dove.breed.entity.ShipmentOutType;
import com.dove.breed.entity.dto.ShipmentEntryTypeDto;
import com.dove.breed.entity.dto.ShipmentOutTypeDto;
import com.dove.breed.entity.vo.ShipmentEntryTypeVo;
import com.dove.breed.mapper.ShipmentEntryTypeMapper;
import com.dove.breed.service.ShipmentEntryTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
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
    @Autowired
    private ConvertUtil convertUtil;
    @Autowired
    private ShipmentEntryTypeService shipmentEntryTypeService;

    @Override
    public List<ShipmentEntryTypeVo> getTypeNameByType(String type) {
        List<ShipmentEntryTypeVo> typeVoList = shipmentEntryTypeMapper.getTypeNameByType(type);
        return typeVoList;
    }

    @Override
    public int save(ShipmentEntryTypeDto shipmentEntryTypeDto) {
        ShipmentEntryType shipmentEntryType = convertUtil.convert(shipmentEntryTypeDto, ShipmentEntryType.class);
        int productNumber = shipmentEntryType.getProductNumber();
        //等于0就是没输入，那就自增
        if (productNumber == 0){
            int maxProductNumber = shipmentEntryTypeMapper.getMaxProductNumber(shipmentEntryType.getBaseId());
            shipmentEntryType.setProductNumber(maxProductNumber + 1);
            int insert = shipmentEntryTypeMapper.insert(shipmentEntryType);
            if (insert == 0){
                return 0;
            }
            return 1;
        }else {
            //用户写了productNumber，那么就查该基地有无这个产品编号
            QueryWrapper<ShipmentEntryType> wrapper = new QueryWrapper<>();
            wrapper.eq("base_id",shipmentEntryType.getBaseId())
                    .eq("product_number",shipmentEntryType.getProductNumber())
                    .eq("is_deleted",0);
            List<ShipmentEntryType> list = shipmentEntryTypeService.list(wrapper);
            if (list.size() != 0){
                return 2;   //即有这个产品编号了，返回
            }
            int insert = shipmentEntryTypeMapper.insert(shipmentEntryType);
            if (insert == 0){
                return 0;
            }
            return 1;
        }
    }
}
