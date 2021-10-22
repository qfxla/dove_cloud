package com.dove.breed.service.impl;

import com.dove.breed.entity.ClearSoil;
import com.dove.breed.entity.vo.ClearSoilVo;
import com.dove.breed.mapper.ClearSoilMapper;
import com.dove.breed.service.ClearSoilService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 清粪信息表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
@Service
public class ClearSoilServiceImpl extends ServiceImpl<ClearSoilMapper, ClearSoil> implements ClearSoilService {

    @Resource
    private ClearSoilMapper clearSoilMapper;
    @Override
    public List<ClearSoilVo> listByType(Long baseId, String dovecoteNumber, String operator, String startTime, String endTime, Long enterpriseId) {
        return clearSoilMapper.listByType(baseId, dovecoteNumber, operator,startTime, endTime,enterpriseId);
    }

    @Override
    public List<String> getAllOperator() {
        return clearSoilMapper.getAllOperator();
    }
}
