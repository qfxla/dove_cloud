package com.dove.breed.service;

import com.dove.breed.entity.DovecoteEntryType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 鸽棚入仓类型表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
public interface DovecoteEntryTypeService extends IService<DovecoteEntryType> {

    /**
     * 获取饲料类型
     * @return
     */
    List<String> getFeedType();
}
