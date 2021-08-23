package com.dove.processing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.processing.entity.OutProcessingBill;
import com.dove.processing.entity.Vo.BillBindInfoVo;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.OutProcessingBillVo;

import java.util.List;

/**
 * <p>
 * 加工厂出库单表 服务类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
public interface OutProcessingBillService extends IService<OutProcessingBill> {

    Page<OutProcessingBillVo> getBillInfoByPage(int no, int size);

    Page<BusinessProcessingVo> getBusInfoByBossId(Long consignee, int no , int size);

    Page<OutProcessingBillVo> getBillByLikeSearch(String value ,int no,int size);

    void saveList(List<OutProcessingBillVo> list);

    List<BillBindInfoVo> getBillInfosByNoPage();
}
