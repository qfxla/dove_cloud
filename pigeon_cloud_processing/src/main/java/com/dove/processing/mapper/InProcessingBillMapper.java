package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.InProcessingBill;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.InProcessingBothVo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 加工厂入库单表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */

public interface InProcessingBillMapper extends BaseMapper<InProcessingBill> {

    List<InProcessingBothVo> getInBillsByNoPage();

    List<BusinessProcessingVo> getBusInfosBySource(@Param("source") Long source, @Param("no") int no , @Param("size") int size);
}
