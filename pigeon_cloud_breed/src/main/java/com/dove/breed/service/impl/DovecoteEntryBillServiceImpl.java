package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.*;
import com.dove.breed.entity.dto.*;
import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.mapper.DovecoteEntryBaseMapper;
import com.dove.breed.mapper.DovecoteEntryBillMapper;
import com.dove.breed.mapper.DovecoteEntryTypeMapper;
import com.dove.breed.service.BaseStockService;
import com.dove.breed.service.DovecoteEntryBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;

import com.dove.breed.entity.DovecoteEntryBase;
import com.dove.breed.entity.DovecoteEntryBill;
import com.dove.breed.entity.dto.DovecoteEntryBillDto;
import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.mapper.DovecoteEntryBaseMapper;
import com.dove.breed.mapper.DovecoteEntryBillMapper;
import com.dove.breed.mapper.DovecoteOutBaseMapper;
import com.dove.breed.mapper.DovecoteOutBillMapper;
import com.dove.breed.service.DovecoteEntryBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.ConstantValue;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.NumberUp;
import java.nio.channels.WritePendingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 鸽棚入仓单 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class DovecoteEntryBillServiceImpl extends ServiceImpl<DovecoteEntryBillMapper, DovecoteEntryBill> implements DovecoteEntryBillService {
    @Autowired
    private DovecoteEntryBillMapper dovecoteEntryBillMapper;
    @Autowired
    private ConvertUtil convertUtil;
    @Autowired
    private DovecoteEntryTypeServiceImpl dovecoteEntryTypeService;
    @Autowired
    private DovecoteEntryBaseMapper dovecoteEntryBaseMapper;

    @Autowired
    private DovecoteEntryTypeMapper dovecoteEntryTypeMapper;
    @Autowired
    private BaseStockService baseStockService;
    @Autowired
    private DovecoteEntryBillService dovecoteEntryBillService;

    @Override
    public List<DovecoteEntryBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long dovecoteId) {
        List<DovecoteEntryBillVo> result = dovecoteEntryBillMapper.findBillByGmt_createAndDovecoteId(startTime, endTime, dovecoteId);
        return result;
    }


    @Override
    public IPage<DovecoteEntryBill> getAllOrder(int pageNum, int pageSize, Long baseId, String dovecoteNumber,String startTime,String overTime) {
        Page<DovecoteEntryBill> page = new Page<>(pageNum, pageSize);
        QueryWrapper<DovecoteEntryBill> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id",baseId)
        .eq("type","饲料");
        if (dovecoteNumber != null){
            wrapper.like("dovecote_number",dovecoteNumber);
        }
        if (startTime != null){
            wrapper.ge("gmt_create",startTime);
        }
        if (startTime != null){
            wrapper.le("gmt_create",overTime);
        }
        wrapper.orderByDesc("gmt_create");
        return baseMapper.selectPage(page,wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Long id) {
        QueryWrapper<DovecoteEntryBase> wrapper = new QueryWrapper<>();
        wrapper.eq("dovecote_entry_bill", id);
        dovecoteEntryBaseMapper.delete(wrapper);
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public List<DovecoteEntryBillVo> findBillByDovecoteAndType(Long baseId, String dovecoteNumber, String type) {
        List<DovecoteEntryBillVo> bills = dovecoteEntryBillMapper.findBillByDovecoteAndType(baseId, dovecoteNumber, type);
        return bills;
    }

    @Transactional
    @Override
    public DovecoteEntryBillVo submitDovecoteEntryBill(DovecoteEntryBillDto dovecoteEntryBillDto, List<DovecoteEntryBaseDto> dovecoteEntryBaseDtoList) {
        DovecoteEntryBill dovecoteEntryBill = convertUtil.convert(dovecoteEntryBillDto, DovecoteEntryBill.class);
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        dovecoteEntryBillMapper.insert(dovecoteEntryBill);
        Long billId = dovecoteEntryBillMapper.getLatestBillId();
        lock.unlock();
        int billTotal = 0;
        int billAmount = 0;
        for (DovecoteEntryBaseDto po1 : dovecoteEntryBaseDtoList) {
            po1.setDovecoteEntryBill(billId);
            QueryWrapper<DovecoteEntryType> wrapper = new QueryWrapper<>();
            wrapper.eq("name",po1.getTypeName());
            List<DovecoteEntryType> dovecoteEntryTypeList = dovecoteEntryTypeService.list(wrapper);
            Long typeId = 0L;
            if (dovecoteEntryTypeList.size() != 0){
                typeId = dovecoteEntryTypeList.get(0).getTypeId();
            }
            po1.setTypeId(typeId);
            int total = 0;
            if (po1.getAmount() != null && po1.getUnitPrice() != null){
                total = po1.getAmount() * po1.getUnitPrice();
            }else{
                total = 0;
            }
            po1.setTotal(total);
            billTotal += total;
            billAmount += po1.getAmount();
            DovecoteEntryBase dovecoteEntryBase = convertUtil.convert(po1, DovecoteEntryBase.class);
            int insert = dovecoteEntryBaseMapper.insert(dovecoteEntryBase);
            if (insert <= 0){
                throw new GlobalException(StatusCode.ERROR,"添加订单信息错误");
            }
        }
        if (dovecoteEntryBillDto.getType().equals("饲料") || dovecoteEntryBillDto.getType().equals("药品")){
            cutToStock(dovecoteEntryBillDto.getBaseId(),dovecoteEntryBaseDtoList);
        }
        DovecoteEntryBill dovecoteEntryBill1 = dovecoteEntryBillMapper.selectById(billId);
        dovecoteEntryBill1.setTotal(billTotal);
        dovecoteEntryBill1.setAmount(billAmount);
        dovecoteEntryBillMapper.updateById(dovecoteEntryBill1);
        DovecoteEntryBillVo result = convertUtil.convert(dovecoteEntryBill1, DovecoteEntryBillVo.class);
        return result;
    }

    private int cutToStock(Long baseId,List<DovecoteEntryBaseDto> dovecoteEntryBaseDtoList){
        for (DovecoteEntryBaseDto po : dovecoteEntryBaseDtoList) {
            QueryWrapper<BaseStock> wrapper = new QueryWrapper<>();
            wrapper.eq("type_name",po.getTypeName());
            List<BaseStock> baseStocks = baseStockService.list(wrapper); //typename是唯一的所以list只有一个
            if (baseStocks.size() != 0){                        //如果库房中有这种typename的了
                BaseStock baseStock = baseStocks.get(0);
                baseStock.setAmount(baseStock.getAmount() - po.getAmount());
                boolean b = baseStockService.updateById(baseStock);
                if (!b){
                    throw new GlobalException(StatusCode.ERROR,"库存更新失败");
                }
            }else{                                           //库房中没有这种typename的
                BaseStock baseStock = new BaseStock();
                QueryWrapper<DovecoteEntryType> wrapper2 = new QueryWrapper<>();
                wrapper.eq("name",po.getTypeName());
                //因为name是唯一的，所以结果是null或者这个List只有一个对象
                List<DovecoteEntryType> dovecoteEntryType = dovecoteEntryTypeService.list(wrapper2);
                Long typeId = 0L;
                if (dovecoteEntryType.size() != 0){
                    typeId = dovecoteEntryType.get(0).getTypeId();
                }
                baseStock.setBaseId(baseId);
                baseStock.setTypeId(typeId);
                baseStock.setType(po.getType());
                baseStock.setTypeName(po.getTypeName());
                baseStock.setAmount( -po.getAmount());
                boolean save = baseStockService.save(baseStock);
                if (!save){
                    throw new GlobalException(StatusCode.ERROR,"库存更新失败");
                }
            }
        }
        return 1;
    }

    @Override
    public List<DovecoteEntryBill> getAllEntryByIdAndType(Long baseId, String type) {
        QueryWrapper<DovecoteEntryBill> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id",baseId).
                eq("type",type)
                .eq("is_deleted",0);
        List<DovecoteEntryBill> list = dovecoteEntryBillService.list(wrapper);
        return list;
    }
}
