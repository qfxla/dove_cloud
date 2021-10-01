package com.dove.breed.mapper;

import com.dove.breed.entity.DovecoteEntryBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 鸽棚入仓单 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface DovecoteEntryBillMapper extends BaseMapper<DovecoteEntryBill> {
    List<DovecoteEntryBillVo> findBillByGmt_createAndDovecoteId(@Param("startTime") Date startTime,
                                                                @Param("endTime")Date endTime,
                                                                @Param("shipmentId")Long dovecoteId);

    /**
     * 根据基地id和鸽棚编号获取所有对应当月订单的id
     * @param baseId
     * @param dovecoteNumber
     * @return
     */
    List<Long> getAllIdByBaseIdAndDovecoteNumber(@Param("baseId")Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                                    @Param("lastMonth")String lastMonth);

    List<DovecoteEntryBillVo> findBillByDovecoteAndType(@Param("baseId")Long baseId,
                                                      @Param("dovecoteNumber")String dovecoteNumber,
                                                        @Param("type")String type);
    Long getLatestBillId();

    List<DovecoteEntryBill> getBillByBaseIdAndDateAndType(@Param("baseId")Long baseId, @Param("type")String type,
                                                        @Param("year")int year, @Param("month")int month, @Param("day")int day);

}
