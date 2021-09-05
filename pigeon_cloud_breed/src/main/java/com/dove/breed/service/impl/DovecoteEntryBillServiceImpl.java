package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.DovecoteEntryBase;
import com.dove.breed.entity.DovecoteEntryBill;
import com.dove.breed.entity.dto.DovecoteEntryBaseFodderDto;
import com.dove.breed.entity.dto.DovecoteEntryBillDto;
import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.mapper.DovecoteEntryBaseMapper;
import com.dove.breed.mapper.DovecoteEntryBillMapper;
import com.dove.breed.mapper.DovecoteEntryTypeMapper;
import com.dove.breed.service.DovecoteEntryBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.NumberUp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 鸽棚入仓单 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class DovecoteEntryBillServiceImpl extends ServiceImpl<DovecoteEntryBillMapper, DovecoteEntryBill> implements DovecoteEntryBillService {
    @Autowired
    private DovecoteEntryBillMapper dovecoteEntryBillMapper;

    @Autowired
    private DovecoteEntryBaseMapper dovecoteEntryBaseMapper;

    @Autowired
    private ConvertUtil convertUtil;

    @Autowired
    private DovecoteEntryTypeMapper dovecoteEntryTypeMapper;

    @Override
    public List<DovecoteEntryBillVo> findBillByGmt_createAndBaseId(Date startTime, Date endTime, Long dovecoteId) {
        List<DovecoteEntryBillVo> result = dovecoteEntryBillMapper.findBillByGmt_createAndDovecoteId(startTime, endTime, dovecoteId);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitDovecoteEntryBill(DovecoteEntryBillDto dovecoteEntryBillDto, ArrayList<DovecoteEntryBaseFodderDto> dovecoteEntryBaseFodderDtoList) {
        DovecoteEntryBill dovecoteEntryBill = convertUtil.convert(dovecoteEntryBillDto, DovecoteEntryBill.class);
        baseMapper.insert(dovecoteEntryBill);
        Long billId = dovecoteEntryBill.getId();
        for (DovecoteEntryBaseFodderDto dovecoteEntryBaseFodderDto : dovecoteEntryBaseFodderDtoList) {
            DovecoteEntryBase dovecoteEntryBase = new DovecoteEntryBase();
            //订单号
            dovecoteEntryBase.setDovecoteEntryBill(billId);
            //总数量
            dovecoteEntryBase.setAmount(dovecoteEntryBaseFodderDto.getAmount());
            //单价
            dovecoteEntryBase.setUnitPrice(dovecoteEntryBaseFodderDto.getUnitPrice());
            //总价格
            dovecoteEntryBase.setTotal(dovecoteEntryBaseFodderDto.getTotal());
            //类型
            dovecoteEntryBase.setType(dovecoteEntryBaseFodderDto.getType());
            //饲料名字
            dovecoteEntryBase.setTypeName(dovecoteEntryBaseFodderDto.getTypeName());
            //产品编号
            dovecoteEntryBase.setTypeId(dovecoteEntryTypeMapper.getTypeIdByNameAndSpecificationsAndType(dovecoteEntryBaseFodderDto.getTypeName(),dovecoteEntryBaseFodderDto.getSpecifications()));
            //备注
            dovecoteEntryBase.setRemark(dovecoteEntryBaseFodderDto.getRemark());
            dovecoteEntryBaseMapper.insert(dovecoteEntryBase);
        }
    }

    @Override
    public IPage<DovecoteEntryBill> getAllOrder(Long pageNum, Long pageSize, Long baseId, String dovecoteNumber,String startTime,String overTime) {
        Page<DovecoteEntryBill> page = new Page<>(pageNum, pageSize);
        QueryWrapper<DovecoteEntryBill> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id",baseId)
        .eq("type","饲料");
        if (dovecoteNumber != null){
            wrapper.like("dovecote_number",dovecoteNumber);
        }
        if (startTime != null){
            wrapper.ge("gmt_create",startTime);
        }
        if (startTime != null){
            wrapper.le("gmt_create",overTime);
        }
        wrapper.orderByDesc("gmt_create");
        return baseMapper.selectPage(page,wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Long id) {
        QueryWrapper<DovecoteEntryBase> wrapper = new QueryWrapper<>();
        wrapper.eq("dovecote_entry_bill",id);
        dovecoteEntryBaseMapper.delete(wrapper);
        baseMapper.deleteById(id);
        return true;
    }
}
