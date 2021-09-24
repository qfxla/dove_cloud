package com.dove.breed.service.impl;

import com.dove.breed.entity.Cage;
import com.dove.breed.mapper.CageMapper;
import com.dove.breed.service.CageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@Service
public class CageServiceImpl extends ServiceImpl<CageMapper, Cage> implements CageService {
    @Autowired
    private CageMapper cageMapper;

    @Override
    public int getLayEggsCycleByCageId(Long cageId) {
        Date lastOne = null;
        Date lastTwo = null;
        try {
            lastOne = cageMapper.getLastOneLayEggsByCageId(cageId);
            lastTwo = cageMapper.getLastTwoLayEggsByCageId(cageId);

            if (lastOne == null || lastTwo == null){
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        int cycle = (int)(lastOne.getTime() - lastTwo.getTime())/(24*60*60*1000);
        return cycle;
    }
}
