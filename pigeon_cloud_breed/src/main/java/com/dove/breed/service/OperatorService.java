package com.dove.breed.service;

import com.dove.breed.entity.Operator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.OperatorVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zcj
 * @since 2021-11-03
 */
public interface OperatorService extends IService<Operator> {

    List<OperatorVo> listByType(Long baseId, String dovecoteNumber, String name, Long enterpriseId);

    List<String> comboBox(Long baseId, String dovecoteNumber, Long enterpriseId);
}
