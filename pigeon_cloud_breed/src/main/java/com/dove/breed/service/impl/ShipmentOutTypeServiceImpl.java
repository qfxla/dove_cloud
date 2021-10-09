package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.ShipmentOutType;
import com.dove.breed.entity.dto.ShipmentOutTypeDto;
import com.dove.breed.mapper.ShipmentOutTypeMapper;
import com.dove.breed.service.ShipmentOutBaseService;
import com.dove.breed.service.ShipmentOutTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.ConstantValue;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 基地出库类型表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class ShipmentOutTypeServiceImpl extends ServiceImpl<ShipmentOutTypeMapper, ShipmentOutType> implements ShipmentOutTypeService {
    @Autowired
    private ConvertUtil convertUtil;
    @Autowired
    private ShipmentOutTypeMapper shipmentOutTypeMapper;
    @Autowired
    private ShipmentOutTypeService shipmentOutTypeService;

    @Override
    public int save(ShipmentOutTypeDto shipmentOutTypeDto) {
        ShipmentOutType shipmentOutType = convertUtil.convert(shipmentOutTypeDto, ShipmentOutType.class);
        int productNumber = shipmentOutType.getProductNumber();
        //等于0就是没输入，那就自增
        if (productNumber == 0){
            int maxProductNumber = shipmentOutTypeMapper.getMaxProductNumber(shipmentOutType.getBaseId());
            shipmentOutType.setProductNumber(maxProductNumber + 1);
            int insert = shipmentOutTypeMapper.insert(shipmentOutType);
            if (insert == 0){
                return 0;
            }
            return 1;
        }else {
            //用户写了productNumber，那么就查该基地有无这个产品编号
            QueryWrapper<ShipmentOutType> wrapper = new QueryWrapper<>();
            wrapper.eq("base_id",shipmentOutType.getBaseId())
                    .eq("product_number",shipmentOutType.getProductNumber())
                    .eq("is_deleted",0);
            List<ShipmentOutType> list = shipmentOutTypeService.list(wrapper);
            if (list.size() != 0){
                return 2;   //即有这个产品编号了，返回
            }
            int insert = shipmentOutTypeMapper.insert(shipmentOutType);
            if (insert == 0){
                return 0;
            }
            return 1;
        }
    }
}
