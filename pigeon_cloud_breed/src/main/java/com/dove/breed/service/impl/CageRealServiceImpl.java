package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.CageReal;
import com.dove.breed.entity.vo.CageRealVo;
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
    public List<CageRealVo> getAllCage(Long baseId, String dovecoteNumber) {
        List<CageRealVo> allCage = cageRealMapper.getAllCage(baseId, dovecoteNumber);
        for (CageRealVo cageRealVo : allCage) {
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return allCage;
    }

    @Override
    public List<CageRealVo> getLayEggsTime(Long baseId, String dovecoteNumber) {
        List<CageRealVo> layEggsTime = cageRealMapper.getLayEggsTime(baseId, dovecoteNumber);
        for (CageRealVo cageRealVo : layEggsTime) {
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return layEggsTime;
    }

    @Override
    public List<CageRealVo> getHatchTime(Long baseId, String dovecoteNumber) {
        List<CageRealVo> hatchTime = cageRealMapper.getHatchTime(baseId, dovecoteNumber);
        for (CageRealVo cageRealVo : hatchTime) {
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return hatchTime;
    }

    @Override
    public List<CageRealVo> getFeedTime(Long baseId, String dovecoteNumber) {
        List<CageRealVo> feedTime = cageRealMapper.getFeedTime(baseId, dovecoteNumber);
        for (CageRealVo cageRealVo : feedTime) {
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return feedTime;
    }
}
