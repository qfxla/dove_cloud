package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.Dovecote;
import com.dove.breed.entity.ManualIncubation;
import com.dove.breed.entity.vo.ManualIncubationVo;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.mapper.ManualIncubationMapper;
import com.dove.breed.service.ManualIncubationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * 人工孵化表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-04
 */
@Service
public class ManualIncubationServiceImpl extends ServiceImpl<ManualIncubationMapper, ManualIncubation> implements ManualIncubationService {
    @Autowired
    private ManualIncubationService manualIncubationService;
    @Autowired
    private ManualIncubationMapper manualIncubationMapper;
    @Autowired
    private DovecoteMapper dovecoteMapper;
    @Autowired
    private ConvertUtil convertUtil;

    @Override
    public int addManualIncubationData(Long baseId, String dovecoteNumber, int one, int two, int three, int four) {
        List<ManualIncubation> todayData = manualIncubationMapper.getTodayData(baseId, dovecoteNumber);
        QueryWrapper<Dovecote> wrapper = new QueryWrapper();
        wrapper.eq("dovecote_number",dovecoteNumber).
                eq("base_id",baseId);
        List<Dovecote> dovecotes = dovecoteMapper.selectList(wrapper);
        String breeder = dovecotes.get(0).getDirector();
        Stream<ManualIncubation> manualIncubationStream = todayData.stream().filter(u -> {
            return u.getPmNumber() > 0;
        });
        if (manualIncubationStream.count() > 0){
            return 0;
        }
        if (todayData.size() == 0){
            List<ManualIncubation> list = new ArrayList();
            for (int i = 0;i < 4;i++) {
                ManualIncubation manualIncubation = new ManualIncubation();
                manualIncubation.setBaseId(baseId);
                manualIncubation.setDovecoteNumber(dovecoteNumber);
                manualIncubation.setType(i+1);
                manualIncubation.setBreederName(breeder);
                list.add(manualIncubation);
            }
            list.get(0).setAmNumber(one);
            list.get(1).setAmNumber(two);
            list.get(2).setAmNumber(three);
            list.get(3).setAmNumber(four);
            for (ManualIncubation manualIncubation : list) {
                manualIncubationMapper.insert(manualIncubation);
            }
        }

        if (todayData.size() != 0){
            for (ManualIncubation manualIncubation : todayData) {
                if (manualIncubation.getType() == 1){
                    manualIncubation.setPmNumber(one);
                }else if (manualIncubation.getType() == 2){
                    manualIncubation.setPmNumber(two);
                }else if (manualIncubation.getType() == 3){
                    manualIncubation.setPmNumber(three);
                }else if (manualIncubation.getType() == 4){
                    manualIncubation.setPmNumber(four);
                }
                manualIncubationService.updateById(manualIncubation);
            }
        }
        return 1;
    }

    @Override
    public Page<ManualIncubationVo> getByDovecoteNumber(Long baseId, String dovecoteNumber,int pageNum,int pageSize) {
        List<ManualIncubation> list = manualIncubationMapper.getByDovecoteNumber(baseId, dovecoteNumber);
        List<ManualIncubationVo> list1 = convertUtil.convert(list, ManualIncubationVo.class);
        Page<ManualIncubationVo> page = PageUtil.fourMyPage(list1, 4 * pageNum, 4 * pageSize);
//        Pageable pageable = PageRequest.of(4 * (pageNum - 1), 4 * pageSize);
//        Page<ManualIncubation> pageFromList = PageUtil.createPageFromList(byDovecoteNumber, pageable);
        return page;
    }

    @Override
    public List<ManualIncubationVo> getByDate(Long baseId, int year, int month, int day) {
        List<ManualIncubation> byDate = manualIncubationMapper.getByDate(baseId, year, month, day);
        List<ManualIncubationVo> manualIncubationVoList = convertUtil.convert(byDate, ManualIncubationVo.class);
        return manualIncubationVoList;
    }
}
