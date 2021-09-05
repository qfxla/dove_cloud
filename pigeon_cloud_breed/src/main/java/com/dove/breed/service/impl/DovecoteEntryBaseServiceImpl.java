package com.dove.breed.service.impl;

import com.dove.breed.entity.DovecoteEntryBase;
import com.dove.breed.entity.DovecoteEntryType;
import com.dove.breed.entity.vo.DovecoteEntryBaseShowVo;
import com.dove.breed.entity.vo.DovecoteEntryBaseVo;
import com.dove.breed.mapper.DovecoteEntryBaseMapper;
import com.dove.breed.mapper.DovecoteEntryTypeMapper;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.service.DovecoteEntryBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 鸽棚入仓信息表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class DovecoteEntryBaseServiceImpl extends ServiceImpl<DovecoteEntryBaseMapper, DovecoteEntryBase> implements DovecoteEntryBaseService {

    @Resource
    private DovecoteEntryBaseMapper dovecoteEntryBaseMapper;

    @Resource
    private DovecoteEntryTypeMapper dovecoteEntryTypeMapper;

    @Override
    public List<DovecoteEntryBaseShowVo> getByDovecoteEntryBill(Long dovecoteEntryBill) {
        List<DovecoteEntryBaseShowVo> dovecoteEntryBaseShowVos = dovecoteEntryBaseMapper.getByDovecoteEntryBill(dovecoteEntryBill);
        for (DovecoteEntryBaseShowVo dovecoteEntryBaseShowVo : dovecoteEntryBaseShowVos) {
             String specifications = dovecoteEntryTypeMapper.getFeedSpecificationById(dovecoteEntryBaseShowVo.getTypeId());
            dovecoteEntryBaseShowVo.setSpecifications(specifications);
        }
        return dovecoteEntryBaseShowVos;
    }
}
