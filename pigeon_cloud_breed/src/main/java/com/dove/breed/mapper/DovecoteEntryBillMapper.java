package com.dove.breed.mapper;

import com.dove.breed.entity.DovecoteEntryBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
