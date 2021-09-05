package com.dove.breed.service.impl;

import com.dove.breed.entity.DovecoteEntryType;
import com.dove.breed.mapper.DovecoteEntryTypeMapper;
import com.dove.breed.service.DovecoteEntryTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 鸽棚入仓类型表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class DovecoteEntryTypeServiceImpl extends ServiceImpl<DovecoteEntryTypeMapper, DovecoteEntryType> implements DovecoteEntryTypeService {

    @Autowired
    private DovecoteEntryTypeMapper dovecoteEntryTypeMapper;
    @Override
    public List<String> getFeedName() {
        return dovecoteEntryTypeMapper.getFeedName();
    }

    @Override
    public List<String> getFeedSpecificationsByName(String feedName) {
        return dovecoteEntryTypeMapper.getFeedSpecificationsByName(feedName);
    }
}
