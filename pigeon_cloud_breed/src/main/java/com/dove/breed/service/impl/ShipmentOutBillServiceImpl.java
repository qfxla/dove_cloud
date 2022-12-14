package com.dove.breed.service.impl;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.DovecoteOutBase;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.ShipmentOutBill;
import com.dove.breed.entity.dto.DovecoteOutBaseDto;
import com.dove.breed.entity.dto.DovecoteOutBillDto;
import com.dove.breed.entity.dto.ShipmentOutBillDto;
import com.dove.breed.entity.vo.DovecoteOutBaseVo;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.entity.vo.ManualIncubationVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.mapper.DovecoteOutBaseMapper;
import com.dove.breed.mapper.DovecoteOutBillMapper;
import com.dove.breed.mapper.ShipmentOutBillMapper;
import com.dove.breed.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.GetMonth;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import com.dove.util.SecurityContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import javax.print.attribute.standard.JobSheets;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 基地出库单 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class ShipmentOutBillServiceImpl extends ServiceImpl<ShipmentOutBillMapper, ShipmentOutBill> implements ShipmentOutBillService {
    @Autowired
    private ShipmentOutBillMapper shipmentOutBillMapper;
    @Autowired
    private DovecoteOutBillService dovecoteOutBillService;
    @Autowired
    private ConvertUtil convertUtil;
    @Autowired
    private DovecoteOutBillMapper dovecoteOutBillMapper;
    @Autowired
    private DovecoteOutBaseMapper dovecoteOutBaseMapper;
    @Autowired
    private ShipmentOutBillService shipmentOutBillService;
    @Autowired
    private DovecoteOutBaseService dovecoteOutBaseService;

    @Override
    public List<ShipmentOutBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long baseId) {
        return shipmentOutBillMapper.findBillByGmt_createAndBaseId(startTime,endTime,baseId);
    }

    @Override
    @Transactional
    public int saveBill(ShipmentOutBillDto shipmentOutBillDto) {
        //填写基地出库单
        ShipmentOutBill shipmentOutBill = convertUtil.convert(shipmentOutBillDto, ShipmentOutBill.class);
                    //生成批次号
        String date = GetMonth.getDateToString(shipmentOutBillDto.getOutTime()); //20211005
        DecimalFormat decimalFormat1 = new DecimalFormat("00");
        String format1 = decimalFormat1.format(shipmentOutBill.getBaseId());  //基地id
                                //这是今天该类型的第几批
        int howManyOfToday = shipmentOutBillMapper.getHowManyOfToday(shipmentOutBill.getBaseId(), shipmentOutBill.getType());
        DecimalFormat decimalFormat2 = new DecimalFormat("000");
        String format2 = decimalFormat2.format(++howManyOfToday);  //几天该基地的第几批
        shipmentOutBill.setFarmBatch("Y" + date + format1 + format2);
                    //插入数据库
        int insert = shipmentOutBillMapper.insert(shipmentOutBill);
        if (insert == 0){
            throw new GlobalException(StatusCode.ERROR,"基地出库单插入失败");
        }
        int shipmentOutBillTotal = 0;
        int shipmentOutBillAmount = 0;
                    //获取该基地出库单id
        Long shipmentOutBillId = shipmentOutBillMapper.getLastBill();
        //填写鸽棚出库单
        List<DovecoteOutBillDto> dovecoteOutBillDtoList = shipmentOutBillDto.getDovecoteOutBillDtoList();
        for (DovecoteOutBillDto dovecoteOutBillDto : dovecoteOutBillDtoList) {
            DovecoteOutBill dovecoteOutBill = convertUtil.convert(dovecoteOutBillDto, DovecoteOutBill.class);
                   //填入基地出库单id
            dovecoteOutBill.setShipmentOutBill(shipmentOutBillId);
            dovecoteOutBill.setBaseId(shipmentOutBill.getBaseId());
            dovecoteOutBill.setType(shipmentOutBill.getType());
                   //插入数据库
            int insert1 = dovecoteOutBillMapper.insert(dovecoteOutBill);
            if (insert == 0){
                throw new GlobalException(StatusCode.ERROR,"鸽棚出库单插入失败");
            }
                   //获取该鸽棚出库单id
            Long dovecoteOutBillId = dovecoteOutBillMapper.getLatestBillId();
            List<DovecoteOutBaseDto> dovecoteOutBaseDtoList = dovecoteOutBillDto.getDovecoteOutBaseDtoList();
            int dovecoteOutBillTotal = 0;
            int dovecoteOutBillAmount = 0;
            for (DovecoteOutBaseDto dovecoteOutBaseDto : dovecoteOutBaseDtoList) {
                DovecoteOutBase dovecoteOutBase = convertUtil.convert(dovecoteOutBaseDto, DovecoteOutBase.class);
        //填写鸽棚出库单信息
                dovecoteOutBase.setDovecoteOutBill(dovecoteOutBillId);
                dovecoteOutBase.setType(shipmentOutBill.getType());
                int total = dovecoteOutBase.getAmount() * dovecoteOutBase.getUnitPrice();
                dovecoteOutBase.setTotal(total);
                int insert2 = dovecoteOutBaseMapper.insert(dovecoteOutBase);
                if (insert2 == 0){
                    throw new GlobalException(StatusCode.ERROR,"鸽棚出库单信息插入失败");
                }
                dovecoteOutBillTotal += total;
                dovecoteOutBillAmount += dovecoteOutBase.getAmount();
            }
            DovecoteOutBill byId = dovecoteOutBillService.getById(dovecoteOutBillId);
            byId.setTotal(dovecoteOutBillTotal);
            byId.setAmount(dovecoteOutBillAmount);
            dovecoteOutBillService.updateById(byId);
            shipmentOutBillTotal += dovecoteOutBillTotal;
            shipmentOutBillAmount += dovecoteOutBillAmount;
        }
        ShipmentOutBill byId = shipmentOutBillService.getById(shipmentOutBillId);
        byId.setAmount(shipmentOutBillAmount);
        byId.setTotal(shipmentOutBillTotal);
        shipmentOutBillService.updateById(byId);
        return 1;
    }

    @Override
    public Page<ShipmentOutBillVo> getShipmentOutBill(Long baseId, String type,int pageNum,int pageSize) {
        QueryWrapper<ShipmentOutBill> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id",baseId).eq("type",type).eq("is_deleted",0);
        List<ShipmentOutBill> list = shipmentOutBillService.list(wrapper);
        list = list.stream().sorted(Comparator.comparing(ShipmentOutBill::getOutTime).reversed()).collect(Collectors.toList());
        List<ShipmentOutBillVo> shipmentOutBillVoList = convertUtil.convert(list, ShipmentOutBillVo.class);
        Page page = PageUtil.list2Page(shipmentOutBillVoList, pageNum, pageSize);
        List<ShipmentOutBillVo> records = page.getRecords();
        for (ShipmentOutBillVo record : records) {
            List<DovecoteOutBillVo> list1 = new ArrayList<>();
            Long shipmentOutBillId = record.getId();
            QueryWrapper<DovecoteOutBill> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("shipment_out_bill",shipmentOutBillId).eq("is_deleted",0);
            List<DovecoteOutBill> dovecoteOutBillList = dovecoteOutBillService.list(wrapper1);
            List<DovecoteOutBillVo> dovecoteOutBillVoList = convertUtil.convert(dovecoteOutBillList, DovecoteOutBillVo.class);
            for (DovecoteOutBillVo dovecoteOutBillVo : dovecoteOutBillVoList) {
                Long dovecoteOutBillId = dovecoteOutBillVo.getId();
                QueryWrapper<DovecoteOutBase> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("dovecote_out_bill",dovecoteOutBillId).eq("is_deleted",0);
                List<DovecoteOutBase> dovecoteOutBaseList = dovecoteOutBaseService.list(wrapper2);
                List<DovecoteOutBaseVo> dovecoteOutBaseVoList = convertUtil.convert(dovecoteOutBaseList, DovecoteOutBaseVo.class);
                dovecoteOutBillVo.setDovecoteOutBaseVoList(dovecoteOutBaseVoList);
            }

            record.setDovecoteOutBillVoList(dovecoteOutBillVoList);
        }
        page.setRecords(records);
        return page;
    }

    @Override
    public Page<ShipmentOutBillVo> getShipmentOutBillByDate(Long baseId, String type, Date date, int pageNum, int pageSize) {
        List<ShipmentOutBill> list = shipmentOutBillMapper.getShipmentOutBillByDate(baseId, type, date);
        List<ShipmentOutBillVo> shipmentOutBillVoList = convertUtil.convert(list, ShipmentOutBillVo.class);
        Page page = PageUtil.list2Page(shipmentOutBillVoList, pageNum, pageSize);
        List<ShipmentOutBillVo> records = page.getRecords();
        for (ShipmentOutBillVo record : records) {
            List<DovecoteOutBillVo> list1 = new ArrayList<>();
            Long shipmentOutBillId = record.getId();
            QueryWrapper<DovecoteOutBill> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("shipment_out_bill",shipmentOutBillId).eq("is_deleted",0);
            List<DovecoteOutBill> dovecoteOutBillList = dovecoteOutBillService.list(wrapper1);
            List<DovecoteOutBillVo> dovecoteOutBillVoList = convertUtil.convert(dovecoteOutBillList, DovecoteOutBillVo.class);
            for (DovecoteOutBillVo dovecoteOutBillVo : dovecoteOutBillVoList) {
                Long dovecoteOutBillId = dovecoteOutBillVo.getId();
                QueryWrapper<DovecoteOutBase> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("dovecote_out_bill",dovecoteOutBillId).eq("is_deleted",0);
                List<DovecoteOutBase> dovecoteOutBaseList = dovecoteOutBaseService.list(wrapper2);
                List<DovecoteOutBaseVo> dovecoteOutBaseVoList = convertUtil.convert(dovecoteOutBaseList, DovecoteOutBaseVo.class);
                dovecoteOutBillVo.setDovecoteOutBaseVoList(dovecoteOutBaseVoList);
            }

            record.setDovecoteOutBillVoList(dovecoteOutBillVoList);
        }
        page.setRecords(records);
        return page;

    }

    @Transactional
    @Override
    public int deletedBill(Long billId) {
        if (!shipmentOutBillService.removeById(billId)){
            throw new GlobalException(StatusCode.ERROR,"失败1");
        }
        QueryWrapper<DovecoteOutBill> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("shipment_out_bill",billId).eq("is_deleted",0);
        List<DovecoteOutBill> dovecoteOutBills = dovecoteOutBillService.list(wrapper1);
        for (DovecoteOutBill dovecoteOutBill : dovecoteOutBills) {
            if (dovecoteOutBillService.removeById(dovecoteOutBill.getId())){
                QueryWrapper<DovecoteOutBase> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("dovecote_out_bill",dovecoteOutBill.getId()).eq("is_deleted",0);
                if (!dovecoteOutBaseService.remove(wrapper2)){
                    throw new GlobalException(StatusCode.ERROR,"失败2");
                }
            }else {
                throw new GlobalException(StatusCode.ERROR,"失败3");
            }
        }
        return 1;
    }

    @Override
    public List<JSONObject> getMonthly(Long baseId, String type,int year,int month) {
        //找这个月的鸽棚出库单
        List<DovecoteOutBill> dovecoteBills = shipmentOutBillMapper.getDovecoteBillThisMonth(baseId, type, year, month);
        //"A01":[{},{}]
        Map<String, List<DovecoteOutBill>> map = dovecoteBills.stream().collect(
                Collectors.groupingBy(
                        dovecoteBill -> dovecoteBill.getDovecoteNumber()
                ));
        Map<String,JSONObject> res =  new HashMap();
        for (Map.Entry<String, List<DovecoteOutBill>> entrySet : map.entrySet()) {
            List<DovecoteOutBill> bills = entrySet.getValue();   //同一鸽棚不同订单
            JSONObject jsonObject = new JSONObject();
            if (type.equals("肉鸽") || type.equals("残次品")){
                for (DovecoteOutBill bill : bills) {
                    int total = 0;
                    QueryWrapper<DovecoteOutBase> wrapper = new QueryWrapper<>();
                    wrapper.eq("dovecote_out_bill",bill.getId());
                    List<DovecoteOutBase> bases = dovecoteOutBaseService.list(wrapper);
                    for (DovecoteOutBase base : bases) {
                        if (!jsonObject.containsKey(base.getTypeName())){
                            jsonObject.put(base.getTypeName(),base.getAmount());
                            total += base.getAmount();
                        }else {
                            Integer amount = (Integer)jsonObject.get(base.getTypeName());
                            jsonObject.put(base.getTypeName(),amount + base.getAmount());
                            total += amount;
                        }
                    }
                    jsonObject.put("total",total);
                }
            }else {
                for (DovecoteOutBill bill : bills) {
                    int total = 0;
                    QueryWrapper<DovecoteOutBase> wrapper = new QueryWrapper<>();
                    wrapper.eq("dovecote_out_bill",bill.getId());
                    List<DovecoteOutBase> bases = dovecoteOutBaseService.list(wrapper);
                    for (DovecoteOutBase base : bases) {
                        if (!jsonObject.containsKey(base.getType())){
                            jsonObject.put(base.getType(),base.getAmount());
                            total += base.getAmount();
                        }else {
                            Integer amount = (Integer)jsonObject.get(base.getType());
                            jsonObject.put(base.getType(),amount + base.getAmount());
                            total += amount;
                        }
                    }
                    jsonObject.put("total",total);
                }
            }
            res.put(entrySet.getKey(),jsonObject);
        }
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Map.Entry<String, JSONObject> map1 : res.entrySet()) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("dovecoteNumber",map1.getKey());
            JSONObject value = map1.getValue();
            value.put("dovecoteNumber",map1.getKey());
            jsonObjects.add(value);
        }
        return jsonObjects;
    }

    @Override
    public ShipmentOutBillVo getByFarmBatch(String farmBatch) {

        QueryWrapper<ShipmentOutBill> wrapper = new QueryWrapper<>();
        wrapper.eq("farm_batch",farmBatch).eq("type","肉鸽");
        List<ShipmentOutBill> list = shipmentOutBillService.list(wrapper);
        ShipmentOutBillVo shipmentOutBillVo = convertUtil.convert(list.get(0),ShipmentOutBillVo.class);

//        List<DovecoteOutBillVo> list1 = new ArrayList<>();
        Long shipmentOutBillId = shipmentOutBillVo.getId();
        QueryWrapper<DovecoteOutBill> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("shipment_out_bill",shipmentOutBillId).eq("is_deleted",0);
        List<DovecoteOutBill> dovecoteOutBillList = dovecoteOutBillService.list(wrapper1);
        List<DovecoteOutBillVo> dovecoteOutBillVoList = convertUtil.convert(dovecoteOutBillList, DovecoteOutBillVo.class);
        for (DovecoteOutBillVo dovecoteOutBillVo : dovecoteOutBillVoList) {
            Long dovecoteOutBillId = dovecoteOutBillVo.getId();
            QueryWrapper<DovecoteOutBase> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("dovecote_out_bill",dovecoteOutBillId).eq("is_deleted",0);
            List<DovecoteOutBase> dovecoteOutBaseList = dovecoteOutBaseService.list(wrapper2);
            List<DovecoteOutBaseVo> dovecoteOutBaseVoList = convertUtil.convert(dovecoteOutBaseList, DovecoteOutBaseVo.class);
            dovecoteOutBillVo.setDovecoteOutBaseVoList(dovecoteOutBaseVoList);
        }

        shipmentOutBillVo.setDovecoteOutBillVoList(dovecoteOutBillVoList);
        return shipmentOutBillVo;
    }


    @Override
    public List<JSONObject> getAllTypeAmountOfMonth(Long baseId,int year) {
        List<Map<String, Object>> listMap = shipmentOutBillMapper.getAllTypeAmountOfMonth(baseId,year);
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0;i < 12 ;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("month",i + 1);
            jsonObject.put("meetDove",0);
            jsonObject.put("abnormalDove",0);
            jsonObject.put("doveEggs",0);
            jsonObject.put("doveShit",0);
            jsonObject.put("disabledDove",0);
            list.add(jsonObject);
        }
        for (Map<String, Object> map : listMap) {
            int month = (int)map.get("month");
            JSONObject jsonObject = list.get(month - 1);
            jsonObject.put("month",month);
            switch (map.get("type").toString()){
                case "肉鸽": jsonObject.put("meetDove",Integer.valueOf(map.get("amount").toString()));
                break;
                case "异常鸽": jsonObject.put("abnormalDove",Integer.valueOf(map.get("amount").toString()));
                break;
                case "鸽蛋": jsonObject.put("doveEggs",Integer.valueOf(map.get("amount").toString()));
                break;
                case "鸽粪": jsonObject.put("doveShit",Integer.valueOf(map.get("amount").toString()));
                break;
                case "残次品": jsonObject.put("disabledDove",Integer.valueOf(map.get("amount").toString()));
                break;
            }
        }
        return list;
    }

    @Override
    public List<JSONObject> getKindOfMeetDoveAmountByDate(Long baseId,int pageNum,int pageSize) {
        int start = (pageNum - 1) * pageSize;
        List<Map<String,String>> mapList =  shipmentOutBillMapper.getKindOfMeetDoveAmountByDate(baseId,start,pageSize);
        ArrayList<JSONObject> jsonObjects = new ArrayList<>(mapList.size());
        Map<String, Integer> havingDate = new HashMap<>();
        for (Map<String, String> map : mapList) {
            if (havingDate.containsKey(map.get("date"))){
                JSONObject jsonObject = jsonObjects.get(havingDate.get(map.get("date")));
                QueryWrapper<DovecoteOutBase> wrapper = new QueryWrapper<>();
                wrapper.eq("dovecote_out_bill",map.get("id"));
                List<DovecoteOutBase> bases = dovecoteOutBaseService.list(wrapper);
                for (DovecoteOutBase base : bases) {
                    jsonObject.put(base.getTypeName(),Integer.valueOf(String.valueOf(base.getAmount())) + base.getAmount());
                }
            }else {
                havingDate.put(map.get("date"),jsonObjects.size());
                JSONObject jsonObject = new JSONObject();
                QueryWrapper<DovecoteOutBase> wrapper = new QueryWrapper<>();
                wrapper.eq("dovecote_out_bill",map.get("id"));
                List<DovecoteOutBase> bases = dovecoteOutBaseService.list(wrapper);
                jsonObject.put("五两鸽",0);
                jsonObject.put("六两鸽",0);
                jsonObject.put("七两鸽",0);
                for (DovecoteOutBase base : bases) {
                    jsonObject.put(base.getTypeName(),Integer.valueOf(String.valueOf(base.getAmount())));
                }
                jsonObject.put("date",map.get("date"));
                jsonObjects.add(jsonObject);
            }
        }

        return jsonObjects;
    }

    @Override
    public JSONObject getAllTypeAmountOfYear(Long baseId, int year) {
        List<Map<String, String>> mapList = shipmentOutBillMapper.getAllTypeAmountOfYear(baseId, year);
        JSONObject jsonObject = new JSONObject();
        for (Map<String, String> stringStringMap : mapList) {
            jsonObject.put(stringStringMap.get("type"),stringStringMap.get("amount"));
        }
        return jsonObject;
    }
}
