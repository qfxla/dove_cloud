package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.BaseStock;
import com.dove.breed.entity.ShipmentEntryBase;
import com.dove.breed.entity.ShipmentEntryBill;
import com.dove.breed.entity.ShipmentEntryType;
import com.dove.breed.entity.dto.ShipmentEntryBaseDto;
import com.dove.breed.entity.dto.ShipmentEntryBillDto;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;
import com.dove.breed.mapper.BaseStockMapper;
import com.dove.breed.mapper.ShipmentEntryBaseMapper;
import com.dove.breed.mapper.ShipmentEntryBillMapper;
import com.dove.breed.service.BaseStockService;
import com.dove.breed.service.ShipmentEntryBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.service.ShipmentEntryTypeService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import com.dove.util.SecurityContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 基地进库单 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class ShipmentEntryBillServiceImpl extends ServiceImpl<ShipmentEntryBillMapper, ShipmentEntryBill> implements ShipmentEntryBillService {

    @Autowired
    ShipmentEntryBillMapper shipmentEntryBillMapper;

    @Autowired
    private ShipmentEntryBaseMapper shipmentEntryBaseMapper;

    @Autowired
    private ConvertUtil convertUtil;

    @Autowired
    private ShipmentEntryTypeService shipmentEntryTypeService;

    @Autowired
    private BaseStockMapper baseStockMapper;

    @Autowired
    private BaseStockService baseStockService;

    @Override
    public List<ShipmentEntryBillVo> findBillByGmt_createAndShipmentId(Date startTime, Date endTime,Long baseId) {
        return shipmentEntryBillMapper.findBillByGmt_createAndShipmentId(startTime,endTime,baseId);
    }

    @Transactional
    @Override
    public ShipmentEntryBillVo submitShipmentEntryBill(ShipmentEntryBillDto shipmentEntryBillDto, List<ShipmentEntryBaseDto> shipmentEntryBaseDtoList) {
        ShipmentEntryBill shipmentEntryBill = convertUtil.convert(shipmentEntryBillDto,ShipmentEntryBill.class);
        shipmentEntryBill.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
//        Lock lock = new ReentrantLock();
//        lock.lock();
        shipmentEntryBillMapper.insert(shipmentEntryBill);
        Long billId = shipmentEntryBillMapper.getLatestBillId();
//        lock.unlock();
        int billTotal = 0;
        int billAmount = 0;
        for (ShipmentEntryBaseDto po1 : shipmentEntryBaseDtoList) {
            //把入库信息的订单号设置为添加的入库单单号
            po1.setShipmentEntryBill(billId);
            //查找有无这个typeName对应的typeId，set进去base中
            QueryWrapper<ShipmentEntryType> wrapper = new QueryWrapper<>();
            wrapper.eq("name",po1.getTypeName());
                    //因为name是唯一的，所以结果是null或者这个List只有一个对象
            List<ShipmentEntryType> shipmentEntryType = shipmentEntryTypeService.list(wrapper);
            int typeId = 0;
            if (shipmentEntryType.size() != 0){
                typeId = shipmentEntryType.get(0).getTypeId();
            }
            po1.setTypeId(typeId);
            //设置信息的total
            int total = po1.getAmount() * po1.getUnitPrice();
            int amount = po1.getAmount();
            po1.setTotal(total);
            billAmount += amount;
            billTotal += total;
            ShipmentEntryBase shipmentEntryBase = convertUtil.convert(po1, ShipmentEntryBase.class);
            shipmentEntryBase.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
            //插入入库信息
            int insertBase = shipmentEntryBaseMapper.insert(shipmentEntryBase);
            if (insertBase <= 0) {
                throw new GlobalException(StatusCode.ERROR, "添加入库单信息失败");
            }
        }
        //如果是药品或饲料添加到基地库存中
        if (shipmentEntryBillDto.getType().equals("饲料") || shipmentEntryBillDto.getType().equals("药品")) {
            addToStock(shipmentEntryBillDto.getBaseId(),shipmentEntryBaseDtoList);
        }
        ShipmentEntryBill shipmentEntryBill1 = shipmentEntryBillMapper.selectById(billId);
        shipmentEntryBill1.setTotal(billTotal);
        shipmentEntryBill1.setAmount(billAmount);
        shipmentEntryBillMapper.updateById(shipmentEntryBill1);
        ShipmentEntryBillVo result = convertUtil.convert(shipmentEntryBill1,
                                                        ShipmentEntryBillVo.class);
        return result;
    }

    private int addToStock(Long baseId,List<ShipmentEntryBaseDto> shipmentEntryBaseDtoList){
        for (ShipmentEntryBaseDto po : shipmentEntryBaseDtoList) {
            QueryWrapper<BaseStock> wrapper = new QueryWrapper<>();
            wrapper.eq("type_name",po.getTypeName());
            List<BaseStock> baseStocks = baseStockService.list(wrapper); //typename是唯一的所以list只有一个
            if (baseStocks.size() != 0){                        //如果库房中有这种typename的了
                BaseStock baseStock = baseStocks.get(0);
                baseStock.setAmount(baseStock.getAmount() + po.getAmount());
                boolean b = baseStockService.updateById(baseStock);
                if (!b){
                    throw new GlobalException(StatusCode.ERROR,"库存更新失败");
                }
            }else{                                           //库房中没有这种typename的
                BaseStock baseStock = new BaseStock();
                baseStock.setBaseId(baseId);
                QueryWrapper<ShipmentEntryType> wrapper2 = new QueryWrapper<>();
                wrapper.eq("name",po.getTypeName());
                //因为name是唯一的，所以结果是null或者这个List只有一个对象
                List<ShipmentEntryType> shipmentEntryType = shipmentEntryTypeService.list(wrapper2);
                Long typeId = 0L;
                if (shipmentEntryType.size() != 0){
                    typeId = Long.valueOf(shipmentEntryType.get(0).getTypeId());
                }
                baseStock.setTypeId(typeId);
                baseStock.setType(po.getType());
                baseStock.setTypeName(po.getTypeName());
                baseStock.setAmount(po.getAmount());
                baseStock.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
                boolean save = baseStockService.save(baseStock);
                if (!save){
                    throw new GlobalException(StatusCode.ERROR,"库存更新失败");
                }
            }
        }
        return 1;
    }

}
