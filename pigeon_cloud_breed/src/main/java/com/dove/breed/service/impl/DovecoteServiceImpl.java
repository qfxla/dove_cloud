package com.dove.breed.service.impl;

import com.dove.breed.entity.Dovecote;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.service.DovecoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 鸽棚表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class DovecoteServiceImpl extends ServiceImpl<DovecoteMapper, Dovecote> implements DovecoteService {
    @Autowired
    private DovecoteMapper dovecoteMapper;

    @Override
    public int getMatEggsOfYesterday(Long baseId, String dovecoteNumber) {
        int amount = dovecoteMapper.getMatEggsOfYesterday(baseId, dovecoteNumber);
        return amount;
    }

    @Override
    public List<AbnormalVo> getAbnormalVoOfYesterday(Long baseId, String dovecoteNumber) {
        List<AbnormalVo> AbnormalVoList = dovecoteMapper.getAbnormalVoOfYesterday(baseId, dovecoteNumber);
        return AbnormalVoList;
    }

    @Override
    public List<Long> getAllCageId(Long baseId, String dovecoteNumber) {
        return dovecoteMapper.getAllCageId(baseId,dovecoteNumber);
    }

    @Override
    public int getCurrentXf(Long cageId) {
        return dovecoteMapper.getCurrentXf(cageId);
    }
}
