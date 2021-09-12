package com.dove.breed.service;

import com.dove.breed.entity.DovecoteOutBill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.dto.DovecoteOutBaseDto;
import com.dove.breed.entity.dto.DovecoteOutBillDto;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import javassist.bytecode.LineNumberAttribute;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 鸽棚出仓单 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface DovecoteOutBillService extends IService<DovecoteOutBill> {
    List<DovecoteOutBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long dovecoteId);

    List<DovecoteOutBillVo> findBillByDovecoteAndType(Long baseId,String dovecoteNumber,String type);

    DovecoteOutBillVo submitDovecoteOutBill(DovecoteOutBillDto dovecoteOutBillDto, List<DovecoteOutBaseDto> dovecoteOutBaseDtoList);

    Map<String,Integer> getAllAmountByBaseIdAndDateAndType(Long baseId, String type, int year, int month, int day);

    Map<String,Integer> getAllAmountByBaseIdAndMonthAndType(Long baseId, String type, int year, int month);

    List<DovecoteOutBill> findDovecoteOutBillByTodayAndType(Long baseId,String type);

    List<Map<String,Integer>> getAllDovecoteByTypeAndYearOfMonth(Long baseId, String type, int year);

    List<Map<String,Integer>> getSumOfTypeAndMonthByBaseId(Long baseId);

    List<Map<String,Integer>> getEveryDaySumByType(Long baseId,String type);
}
