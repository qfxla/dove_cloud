package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.InProcessingBill;
import com.dove.processing.entity.Vo.BillBindInfoVo;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.InProcessingBothVo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 加工厂入库单表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
public interface InProcessingBillService extends IService<InProcessingBill> {

    List<InProcessingBothVo> getInPrcessingInfosByNoPage();

    Page<BusinessProcessingVo> getBusInfoBySource(Long source, int no , int size);

}
