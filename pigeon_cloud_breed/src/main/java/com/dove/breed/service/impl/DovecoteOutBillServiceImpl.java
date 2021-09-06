package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.DovecoteOutBase;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.DovecoteOutType;
import com.dove.breed.entity.dto.DovecoteOutBaseDto;
import com.dove.breed.entity.dto.DovecoteOutBillDto;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.mapper.DovecoteOutBaseMapper;
import com.dove.breed.mapper.DovecoteOutBillMapper;
import com.dove.breed.service.DovecoteOutBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.service.DovecoteOutTypeService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmlunit.util.Convert;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 鸽棚出仓单 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class DovecoteOutBillServiceImpl extends ServiceImpl<DovecoteOutBillMapper, DovecoteOutBill> implements DovecoteOutBillService {
    @Autowired
    private DovecoteOutBillMapper dovecoteOutBillMapper;
    @Autowired
    private ConvertUtil convertUtil;
    @Autowired
    private DovecoteOutTypeService dovecoteOutTypeService;
    @Autowired
    private DovecoteOutBaseMapper dovecoteOutBaseMapper;

    @Override
    public List<DovecoteOutBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long dovecoteId) {
        List<DovecoteOutBillVo> result = dovecoteOutBillMapper.findBillByGmt_createAndDovecoteId(startTime, endTime, dovecoteId);
        return result;
    }

    @Override
    public List<DovecoteOutBillVo> findBillByDovecoteAndType(Long baseId, String dovecoteNumber, String type) {
        List<DovecoteOutBillVo> bills = dovecoteOutBillMapper.findBillByDovecoteAndType(baseId, dovecoteNumber, type);
        return bills;
    }

    @Override
    public DovecoteOutBillVo submitDovecoteOutBill(DovecoteOutBillDto dovecoteOutBillDto, List<DovecoteOutBaseDto> dovecoteOutBaseDtoList) {
        DovecoteOutBill dovecoteOutBill = convertUtil.convert(dovecoteOutBillDto, DovecoteOutBill.class);
        Lock lock = new ReentrantLock();
        lock.lock();
        dovecoteOutBillMapper.insert(dovecoteOutBill);
        Long billId = dovecoteOutBillMapper.getLatestBillId();
        lock.unlock();
        int billTotal = 0;
        int billAmount = 0;
        for (DovecoteOutBaseDto po1 : dovecoteOutBaseDtoList) {
            //把入库信息的订单号设置为添加的入库单单号
            po1.setDovecoteOutBill(billId);
            //查找有无这个typeName对应的typeId，set进去base中
            QueryWrapper<DovecoteOutType> wrapper = new QueryWrapper<>();
            wrapper.eq("name",po1.getTypeName());
            //因为name是唯一的，所以结果是null或者List只有一个对象
            List<DovecoteOutType> dovecoteOutTypeList = dovecoteOutTypeService.list(wrapper);
            Long typeId = 0L;
            if (dovecoteOutTypeList.size() != 0){
                typeId = dovecoteOutTypeList.get(0).getTypeId();
            }
            po1.setTypeId(typeId);
            //设置base的total
            int total = 0;
            if (po1.getUnitPrice() != null){
                total = po1.getAmount() * po1.getUnitPrice();
            }else {
                total = 0;
            }
            po1.setTotal(total);
            billTotal += total;
            billAmount += po1.getAmount();
            DovecoteOutBase dovecoteOutBase = convertUtil.convert(po1, DovecoteOutBase.class);
            //插入出库信息
            int insert = dovecoteOutBaseMapper.insert(dovecoteOutBase);
            if (insert <= 0){
                throw new GlobalException(StatusCode.ERROR,"添加订单信息错误");
            }
        }
        DovecoteOutBill dovecoteOutBill1 = dovecoteOutBillMapper.selectById(billId);
        dovecoteOutBill1.setTotal(billTotal);
        dovecoteOutBill1.setAmount(billAmount);
        dovecoteOutBillMapper.updateById(dovecoteOutBill1);
        DovecoteOutBillVo result = convertUtil.convert(dovecoteOutBill1, DovecoteOutBillVo.class);
        return result;

    }
}
