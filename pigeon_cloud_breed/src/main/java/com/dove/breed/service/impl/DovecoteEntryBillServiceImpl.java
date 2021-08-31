package com.dove.breed.service.impl;

import com.dove.breed.entity.DovecoteEntryBill;
import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.mapper.DovecoteEntryBillMapper;
import com.dove.breed.service.DovecoteEntryBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 鸽棚入仓单 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class DovecoteEntryBillServiceImpl extends ServiceImpl<DovecoteEntryBillMapper, DovecoteEntryBill> implements DovecoteEntryBillService {
    @Autowired
    private DovecoteEntryBillMapper dovecoteEntryBillMapper;

    @Override
    public List<DovecoteEntryBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long dovecoteId) {
        List<DovecoteEntryBillVo> result = dovecoteEntryBillMapper.findBillByGmt_createAndDovecoteId(startTime, endTime, dovecoteId);
        return result;
    }
}
