package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.DovecoteEntryBase;
import com.dove.breed.entity.DovecoteEntryBill;
import com.dove.breed.entity.dto.DovecoteEntryBaseFodderDto;
import com.dove.breed.entity.dto.DovecoteEntryBillDto;
import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.mapper.DovecoteEntryBaseMapper;
import com.dove.breed.mapper.DovecoteEntryBillMapper;
import com.dove.breed.mapper.DovecoteEntryTypeMapper;
import com.dove.breed.service.DovecoteEntryBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;

import com.dove.breed.entity.DovecoteEntryBase;
import com.dove.breed.entity.DovecoteEntryBill;
import com.dove.breed.entity.DovecoteEntryType;
import com.dove.breed.entity.DovecoteOutType;
import com.dove.breed.entity.dto.DovecoteEntryBaseDto;
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

    @Override
    public List<DovecoteEntryBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long dovecoteId) {
        List<DovecoteEntryBillVo> result = dovecoteEntryBillMapper.findBillByGmt_createAndDovecoteId(startTime, endTime, dovecoteId);
        return result;
    }


    @Override
    public IPage<DovecoteEntryBill> getAllOrder(Long pageNum, Long pageSize, Long baseId, String dovecoteNumber,String startTime,String overTime) {
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
        DovecoteEntryBill dovecoteEntryBill1 = dovecoteEntryBillMapper.selectById(billId);
        dovecoteEntryBill1.setTotal(billTotal);
        dovecoteEntryBill1.setAmount(billAmount);
        dovecoteEntryBillMapper.updateById(dovecoteEntryBill1);
        DovecoteEntryBillVo result = convertUtil.convert(dovecoteEntryBill1, DovecoteEntryBillVo.class);
        return result;
    }
}
