package com.dove.breed.service.impl;

import com.dove.breed.entity.CagePosition;
import com.dove.breed.mapper.CagePositionMapper;
import com.dove.breed.service.CagePositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@Service
public class CagePositionServiceImpl extends ServiceImpl<CagePositionMapper, CagePosition> implements CagePositionService {
    @Autowired
    private CagePositionMapper cagePositionMapper;

    @Override
    public Long getCageId(Long baseId, String dovecoteNumber, int rowNo, int line, int columnNo) {
        Long cageId = cagePositionMapper.getCageId(baseId, dovecoteNumber, rowNo, line, columnNo);
        return cageId;
    }
}
