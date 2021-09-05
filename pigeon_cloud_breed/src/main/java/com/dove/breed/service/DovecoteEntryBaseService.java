package com.dove.breed.service;

import com.dove.breed.entity.DovecoteEntryBase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.DovecoteEntryBaseShowVo;
import com.dove.breed.entity.vo.DovecoteEntryBaseVo;

import java.util.List;

/**
 * <p>
 * 鸽棚入仓信息表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface DovecoteEntryBaseService extends IService<DovecoteEntryBase> {

    /**
     * 通过订单号查询订单详细信息
     * @param dovecoteEntryBill
     * @return
     */
    List<DovecoteEntryBaseShowVo> getByDovecoteEntryBill(Long dovecoteEntryBill);
}
