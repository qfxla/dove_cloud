package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.BreedBase;
import com.dove.breed.entity.DovecoteEntryBase;
import com.dove.breed.entity.DovecoteEntryType;
import com.dove.breed.entity.FeedStock;
import com.dove.breed.entity.vo.*;
import com.dove.breed.mapper.*;
import com.dove.breed.service.BreedBaseService;
import com.dove.breed.service.DovecoteEntryBaseService;
import com.dove.breed.service.FeedStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 月底饲料盘点表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
// TODO 先与前端说好再写

@Service
public class FeedStockServiceImpl extends ServiceImpl<FeedStockMapper, FeedStock> implements FeedStockService {

    @Autowired
    private FeedStockMapper feedStockMapper;

    @Autowired
    private DovecoteEntryBaseMapper dovecoteEntryBaseMapper;

    @Autowired
    private DovecoteEntryTypeMapper dovecoteEntryTypeMapper;

    @Autowired
    private ShipmentEntryTypeMapper shipmentEntryTypeMapper;


    @Override
    public List<UseOfFeedVo> getUseOfFeedMonth(Long baseId, String dovecoteNumber, int year, int month) {
        //获取所有基地的typeName存在useOfFeedVoList
        List<ShipmentEntryTypeVo> typeNameList = shipmentEntryTypeMapper.getTypeNameByType("饲料");
        List<UseOfFeedVo> useOfFeedVoList = new ArrayList<>();
        for (ShipmentEntryTypeVo po : typeNameList) {
            String typeName = po.getName();
            UseOfFeedVo useOfFeedVo = new UseOfFeedVo();
            useOfFeedVo.setTypeName(typeName);
            useOfFeedVo.setAmount(0);
            useOfFeedVoList.add(useOfFeedVo);
        }
        List<DovecoteEntryBaseVo> dovecoteEntryBaseVoList = dovecoteEntryBaseMapper.getFeedEntryOfMonth(baseId, dovecoteNumber, year, month);
        List<FeedStockVo> feedAmountOfMonth = feedStockMapper.getFeedAmountOfMonth(baseId, dovecoteNumber, year, month);
        List<FeedStockVo> feedAmountOfLastMonth = null;
        if (month == 1){
            feedAmountOfLastMonth = feedStockMapper.getFeedAmountOfMonth(baseId, dovecoteNumber, year - 1, 12);
        }else {
            feedAmountOfLastMonth = feedStockMapper.getFeedAmountOfMonth(baseId, dovecoteNumber, year, month - 1);
        }
        //遍历所有typeName，进货的对应+，上个月对应+，这个月对应-
        for (UseOfFeedVo useOfFeedVo : useOfFeedVoList) {
            for (DovecoteEntryBaseVo dovecoteEntryBaseVo : dovecoteEntryBaseVoList) {
                //遍历所有进货类型，有相等的就相加，下面同理
                String typeName = dovecoteEntryBaseVo.getTypeName();
                String typeName1 = useOfFeedVo.getTypeName();
                boolean flag = false;
                if (!typeName.isEmpty()){
                    flag = (typeName.equals(typeName1));
                }
                if (flag){
                    int amount = useOfFeedVo.getAmount() + dovecoteEntryBaseVo.getAmount();
                    useOfFeedVo.setAmount(amount);
                }
            }
            for (FeedStockVo feedStockVo : feedAmountOfMonth) {
                String feedType = feedStockVo.getFeedType();
                String typeName = useOfFeedVo.getTypeName();
                boolean flag = false;
                if (!feedType.isEmpty()){
                    flag = (feedType.equals(typeName));
                }
                if(flag){
                    int amount = useOfFeedVo.getAmount() - feedStockVo.getAmount();
                    useOfFeedVo.setAmount(amount);
                }
            }
            for (FeedStockVo feedStockVo : feedAmountOfLastMonth) {
                String feedType = feedStockVo.getFeedType();
                String typeName = useOfFeedVo.getTypeName();
                boolean flag = false;
                if (!feedType.isEmpty()){
                    flag = feedType.equals(typeName);
                }
                if (flag){
                    int amount = useOfFeedVo.getAmount() + feedStockVo.getAmount();
                    useOfFeedVo.setAmount(amount);
                }
            }
        }
        return useOfFeedVoList;
    }
}
