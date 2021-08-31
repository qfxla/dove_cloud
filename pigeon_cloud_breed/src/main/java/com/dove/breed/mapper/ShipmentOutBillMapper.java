package com.dove.breed.mapper;

import com.dove.breed.entity.ShipmentOutBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

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
}
