package com.dove.breed.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.ShipmentOutBill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.dto.ShipmentOutBillDto;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基地出库单 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface ShipmentOutBillService extends IService<ShipmentOutBill> {
    List<ShipmentOutBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long baseId);

    int saveBill(ShipmentOutBillDto shipmentOutBillDto);

    Page<ShipmentOutBillVo> getShipmentOutBill(Long baseId, String type, int pageNum, int pageSize);

    Page<ShipmentOutBillVo> getShipmentOutBillByDate(Long baseId,String type,Date date,int pageNum,int pageSize);

    int deletedBill(Long billId);

    Map<String, JSONObject> getMonthly(Long baseId, String type, int year, int month);

    ShipmentOutBillVo getByFarmBatch(String farmBatch,Long baseId,String type);

    List<JSONObject> getAllTypeAmountOfMonth(Long baseId,int year);

    List<JSONObject> getKindOfMeetDoveAmountByDate(Long baseId,int pageNum,int pageSize);

    JSONObject getAllTypeAmountOfYear(Long baseId, int year);
}
