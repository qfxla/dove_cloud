package com.dove.breed.service.impl;

import com.dove.breed.entity.Operator;
import com.dove.breed.entity.vo.OperatorVo;
import com.dove.breed.mapper.OperatorMapper;
import com.dove.breed.service.OperatorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-11-03
 */
@Service
public class OperatorServiceImpl extends ServiceImpl<OperatorMapper, Operator> implements OperatorService {

    @Resource
    private OperatorMapper operatorMapper;

    @Override
    public List<OperatorVo> listByType(Long baseId, String dovecoteNumber, String name, Long enterpriseId) {
        return operatorMapper.listByType(baseId, dovecoteNumber, name, enterpriseId);
    }

    @Override
    public List<String> comboBox(Long baseId, String dovecoteNumber, Long enterpriseId) {
        return operatorMapper.comboBox(baseId, dovecoteNumber, enterpriseId);
    }
}
