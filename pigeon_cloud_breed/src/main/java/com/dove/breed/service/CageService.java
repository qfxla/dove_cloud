package com.dove.breed.service;

import com.dove.breed.entity.Cage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
public interface CageService extends IService<Cage> {
    int getLayEggsCycleByCageId(Long cageId);
}
