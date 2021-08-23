package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.OutProcessingBill;
import com.dove.processing.entity.Vo.BillBindInfoVo;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.OutProcessingBillVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 加工厂出库单表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */

public interface OutProcessingBillMapper extends BaseMapper<OutProcessingBill> {

    List<OutProcessingBillVo> getBillByPage(@Param("no") int no ,@Param("size") int size);

    List<BusinessProcessingVo> getBusInfoByBossId(@Param("consignee") Long consignee, @Param("no") int no , @Param("size") int size);

    List<OutProcessingBillVo> getBillsByLikeSearch(@Param("value") String value,@Param("no") int no, @Param("size") int size);

    List<BillBindInfoVo> getBillsByNoPage();
}
