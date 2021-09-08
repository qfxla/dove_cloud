package com.dove.breed.mapper;

import com.dove.breed.entity.DovecoteOutBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 鸽棚出仓单 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface DovecoteOutBillMapper extends BaseMapper<DovecoteOutBill> {
    List<DovecoteOutBillVo> findBillByGmt_createAndDovecoteId(@Param("startTime") Date startTime,
                                                              @Param("endTime")Date endTime,
                                                              @Param("shipmentId")Long dovecoteId);
    List<DovecoteOutBillVo> findBillByDovecoteAndType(@Param("baseId")Long baseId,
                                                      @Param("dovecoteNumber")String dovecoteNumber,
                                                      @Param("type")String type);
    Long getLatestBillId();

    List<DovecoteOutBill> getBillByBaseIdAndDateAndType(@Param("baseId")Long baseId,@Param("type")String type,
                                                        @Param("year")int year,@Param("month")int month,@Param("day")int day);

    List<DovecoteOutBill> getBillByBaseIdAndMonthAndType(@Param("baseId")Long baseId,@Param("type")String type,
                                                        @Param("year")int year,@Param("month")int month);

}
