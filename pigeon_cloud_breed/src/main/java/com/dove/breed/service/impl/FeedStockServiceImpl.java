package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.DovecoteEntryBase;
import com.dove.breed.entity.DovecoteEntryType;
import com.dove.breed.entity.FeedStock;
import com.dove.breed.entity.vo.DovecoteEntryBaseVo;
import com.dove.breed.entity.vo.FeedStockVo;
import com.dove.breed.mapper.DovecoteEntryBaseMapper;
import com.dove.breed.mapper.DovecoteEntryTypeMapper;
import com.dove.breed.mapper.FeedStockMapper;
import com.dove.breed.service.DovecoteEntryBaseService;
import com.dove.breed.service.FeedStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<FeedStockVo> getFeedAmountOfMonth(String baseName, String dovecoteNumber, int year, int month) {
        if (month > 1){
            List<FeedStockVo> list = feedStockMapper.getFeedAmountOfMonth(baseName, dovecoteNumber, year, month);
            List<FeedStockVo> LastList = feedStockMapper.getFeedAmountOfMonth(baseName, dovecoteNumber, year, month - 1);
            List<DovecoteEntryBaseVo> feedEntryOfMonthVo = dovecoteEntryBaseMapper.getFeedEntryOfMonth(baseName, dovecoteNumber, year, month);

            QueryWrapper<DovecoteEntryType> wrapper = new QueryWrapper<>();
            wrapper.eq("type","饲料");
            List<DovecoteEntryType> typeList = dovecoteEntryTypeMapper.selectList(wrapper);
            typeList.forEach(type -> {

            });

            Map<String,Integer> map = new HashMap<>(16,0.75f);
            LastList.forEach(po1 -> {
                map.put(po1.getFeedType(),po1.getAmount());
            });
            list.forEach(po2 -> {
                //TODO 因为list有可能张三牌饲料为0时，饲养员是不会记录的，所以一个默认为0，和前端商量，用表单提交所有饲料的数量
                if(!map.containsKey(po2.getFeedType())){
                    map.put(po2.getFeedType(),(-1)*po2.getAmount());
                }

            });
        }


        return null;
    }
}
