package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.DovecoteEntryBase;
import com.dove.breed.entity.DovecoteOutBase;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.DovecoteOutType;
import com.dove.breed.entity.dto.DovecoteOutBaseDto;
import com.dove.breed.entity.dto.DovecoteOutBillDto;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.mapper.DovecoteOutBaseMapper;
import com.dove.breed.mapper.DovecoteOutBillMapper;
import com.dove.breed.service.DovecoteEntryBaseService;
import com.dove.breed.service.DovecoteOutBaseService;
import com.dove.breed.service.DovecoteOutBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.service.DovecoteOutTypeService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.GetMonth;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xmlunit.util.Convert;

import java.lang.ref.WeakReference;
import java.util.*;
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
    @Autowired
    private DovecoteOutBaseService dovecoteOutBaseService;

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

    @Transactional
    @Override
    public DovecoteOutBillVo submitDovecoteOutBill(DovecoteOutBillDto dovecoteOutBillDto, List<DovecoteOutBaseDto> dovecoteOutBaseDtoList) {
        DovecoteOutBill dovecoteOutBill = convertUtil.convert(dovecoteOutBillDto, DovecoteOutBill.class);
        //根据日期生成批次号
        String farmBatch = GetMonth.getDateToString();
        dovecoteOutBill.setFarmBatch(farmBatch);
        dovecoteOutBill.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        dovecoteOutBill.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
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
            dovecoteOutBase.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
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

    @Override
    public Map<String, Integer> getAllAmountByBaseIdAndDateAndType(Long baseId, String type, int year, int month, int day) {
        List<DovecoteOutBill> bills = dovecoteOutBillMapper.getBillByBaseIdAndDateAndType(baseId, type, year, month, day);
        Map<String, Integer> map = new HashMap<>();
        int amount = 0;
        for (DovecoteOutBill bill : bills) {
            QueryWrapper<DovecoteOutBase> wrapper = new QueryWrapper<>();
            wrapper.eq("dovecote_out_bill",bill.getId())
                    .eq("is_deleted",0);
            List<DovecoteOutBase> bases = dovecoteOutBaseService.list(wrapper);
            for (DovecoteOutBase base : bases) {
                amount += base.getAmount();
                if (!map.containsKey(base.getTypeName())){
                    map.put(base.getTypeName(),base.getAmount());
                }else {
                    map.put(base.getTypeName(),map.get(base.getTypeName())+base.getAmount());
                }
            }
        }
        map.put("amount",amount);
        return map;
    }

    @Override
    public Map<String, Integer> getAllAmountByBaseIdAndMonthAndType(Long baseId, String type, int year, int month) {
        List<DovecoteOutBill> bills = dovecoteOutBillMapper.getBillByBaseIdAndMonthAndType(baseId, type, year, month);
        Map<String, Integer> map = new HashMap<>();
        for (DovecoteOutBill bill : bills) {
            QueryWrapper<DovecoteOutBase> wrapper = new QueryWrapper<>();
            wrapper.eq("dovecote_out_bill",bill.getId())
                    .eq("is_deleted",0);
            List<DovecoteOutBase> bases = dovecoteOutBaseService.list(wrapper);
            for (DovecoteOutBase base : bases) {
                if (!map.containsKey(base.getTypeName())){
                    map.put(base.getTypeName(),base.getAmount());
                }else {
                    map.put(base.getTypeName(),map.get(base.getTypeName())+base.getAmount());
                }
            }
        }
        int amount = 0;
        for (Map.Entry<String, Integer> entrySet : map.entrySet()) {
            Integer value = entrySet.getValue();
            amount += value;
        }
        map.put("amount",amount);
        return map;
    }

    @Override
    public List<DovecoteOutBill> findDovecoteOutBillByTodayAndType(Long baseId,String type) {
        List<DovecoteOutBill> dovecoteOutBill = dovecoteOutBillMapper.findDovecoteOutBillByTodayAndType(baseId,type);
        return dovecoteOutBill;
    }

    @Override
    public List<Map<String, Integer>> getAllDovecoteByTypeAndYearOfMonth(Long baseId, String type, int year) {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        List<Map<String, Integer>> list = new ArrayList<>();
        for (int i = 1;i < month + 2;i++){
            Map<String, Integer> map = getAllAmountByBaseIdAndMonthAndType(baseId, type, year, i);
            map.put("month",i);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Integer>> getSumOfTypeAndMonthByBaseId(Long baseId) {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        List<Map<String, Integer>> list = new ArrayList<>();
        for (int i = 1;i < month + 2;i++){
            HashMap<String, Integer> map = new HashMap<>();
            map.put("肉鸽",0);
            map.put("鸽蛋",0);
            map.put("残次品",0);
            map.put("鸽粪重量",0);
            map.put("month",i);
            List<DovecoteOutBill> list1 = dovecoteOutBillMapper.getSumOfTypeAndMonthByBaseId(baseId, i);
            for (DovecoteOutBill dovecoteOutBill : list1) {
               if (dovecoteOutBill.getType().equals("肉鸽")){
                        map.put("肉鸽",map.get("肉鸽") + dovecoteOutBill.getAmount());
                    }else if(dovecoteOutBill.getType().equals("鸽蛋")){
                        map.put("鸽蛋",map.get("鸽蛋") + dovecoteOutBill.getAmount());
                    }else if(dovecoteOutBill.getType().equals("残次品")){
                        map.put("残次品",map.get("残次品") + dovecoteOutBill.getAmount());
                    }else if(dovecoteOutBill.getType().equals("鸽粪重量")){
                        map.put("鸽粪重量",map.get("鸽粪重量") + dovecoteOutBill.getAmount());
                }
            }
            list.add(map);
        }
        return list;
    }

    public List<Map<String,Integer>> getEveryDaySumByType(Long baseId,String type){
        ArrayList<Map<String, Integer>> list = new ArrayList<>();
        for(int i = 0;i < 30;i++){
            Map<String, Integer> map = GetMonth.getDay(-i);
            int day = map.get("day");
            int month = map.get("month");
            int year = map.get("year");
            Map<String, Integer> map1 = getAllAmountByBaseIdAndDateAndType(baseId, type, year, month, day);
            map1.put("year",year);
            map1.put("month",month);
            map1.put("day",day);
            list.add(map1);
        }
        return list;
    }

}
