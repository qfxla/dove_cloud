package com.dove.breed.service;

import com.dove.breed.entity.ManualIncubation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 人工孵化表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-04
 */
public interface ManualIncubationService extends IService<ManualIncubation> {
    int addManualIncubationData(Long baseId,String dovecoteNumber,
                                int one,int two,int three,int four);
}
