package com.dove.breed.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.ShipmentOutBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基地出库单 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface ShipmentOutBillMapper extends BaseMapper<ShipmentOutBill> {
    List<ShipmentOutBillVo> findBillByGmt_createAndBaseId(@Param("startTime") Date startTime, @Param("endTime")Date endTime, @Param("baseId")Long baseId);

    int getHowManyOfToday(@Param("baseId")Long baseId,@Param("type")String type);
    Long getLastBill();

    List<ShipmentOutBill> getShipmentOutBillByDate(@Param("baseId")Long baseId, @Param("type")String type,
                                                   @Param("date")Date date);
    List<DovecoteOutBill> getDovecoteBillThisMonth(@Param("baseId")Long baseId, @Param("type")String type,
                                                   @Param("year")int year, @Param("month")int month);

    List<Map<String,Object>> getAllTypeAmountOfMonth(@Param("baseId") Long baseId,@Param("year")int year);

    List<Map<String, String>> getKindOfMeetDoveAmountByDate(@Param("baseId") Long baseId,
                                                            @Param("start") int start,
                                                            @Param("pageSize") int pageSize);

    List<Map<String,String>> getAllTypeAmountOfYear(@Param("baseId") Long baseId,@Param("year") int year);
}
