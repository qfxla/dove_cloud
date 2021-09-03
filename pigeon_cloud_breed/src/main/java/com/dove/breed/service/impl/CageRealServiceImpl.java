package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.CageReal;
import com.dove.breed.mapper.CageRealMapper;
import com.dove.breed.service.CageRealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-01
 */
@Service
public class CageRealServiceImpl extends ServiceImpl<CageRealMapper, CageReal> implements CageRealService {
    @Autowired
    private CageRealMapper cageRealMapper;
    @Autowired
    private ConvertUtil convertUtil;

    @Override
    public List<CageReal> getAllCage(Long baseId, String dovecoteNumber) {
        List<CageReal> allCage = cageRealMapper.getAllCage(baseId, dovecoteNumber);
        return allCage;
    }

    @Override
    public List<CageReal> getLayEggsTime(Long baseId, String dovecoteNumber) {
        List<CageReal> layEggsTime = cageRealMapper.getLayEggsTime(baseId, dovecoteNumber);
        return layEggsTime;
    }

    @Override
    public List<CageReal> getHatchTime(Long baseId, String dovecoteNumber) {
        List<CageReal> hatchTime = cageRealMapper.getHatchTime(baseId, dovecoteNumber);
        return hatchTime;
    }

    @Override
    public List<CageReal> getFeedTime(Long baseId, String dovecoteNumber) {
        List<CageReal> feedTime = cageRealMapper.getFeedTime(baseId, dovecoteNumber);
        return feedTime;
    }
}
