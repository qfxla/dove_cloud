package com.dove.breed.mapper;

import com.dove.breed.entity.DovecoteEntryBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.DovecoteEntryBaseShowVo;
import com.dove.breed.entity.vo.DovecoteEntryBaseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 鸽棚入仓信息表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface DovecoteEntryBaseMapper extends BaseMapper<DovecoteEntryBase> {
    List<DovecoteEntryBaseVo> getFeedEntryOfMonth(@Param("baseId")Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                                  @Param("year")int year, @Param("month")int month);

    /**
     * 根据订单获取鸽棚入仓信息
     * @param billId
     * @return
     */
    List<DovecoteEntryBase> getAllByBillId( @Param("billId")Integer billId);

    /**
     * 通过订单号查询订单的详细信息
     * @param dovecoteEntryBill
     * @return
     */
    List<DovecoteEntryBaseShowVo> getByDovecoteEntryBill(@Param("dovecoteEntryBill")Long dovecoteEntryBill);
}
