package com.dove.breed.service.impl;

import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.mapper.DovecoteOutBillMapper;
import com.dove.breed.service.DovecoteOutBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 鸽棚出仓单 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class DovecoteOutBillServiceImpl extends ServiceImpl<DovecoteOutBillMapper, DovecoteOutBill> implements DovecoteOutBillService {
    @Autowired
    private DovecoteOutBillMapper dovecoteOutBillMapper;

    @Override
    public List<DovecoteOutBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long dovecoteId) {
        List<DovecoteOutBillVo> result = dovecoteOutBillMapper.findBillByGmt_createAndDovecoteId(startTime, endTime, dovecoteId);
        return result;
    }

    @Override
    public List<DovecoteOutBillVo> findBillByDovecoteAndType(Long baseId, String dovecoteNumber, String type) {
        List<DovecoteOutBillVo> bills = dovecoteOutBillMapper.findBillByDovecoteAndType(baseId, dovecoteNumber, type);
        return bills;
    }
}
