package com.dove.breed.service.impl;

import com.dove.breed.entity.DovecoteDaily;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.mapper.DovecoteDailyMapper;
import com.dove.breed.service.DovecoteDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 鸽棚日结表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-02
 */
@Service
public class DovecoteDailyServiceImpl extends ServiceImpl<DovecoteDailyMapper, DovecoteDaily> implements DovecoteDailyService {
    @Autowired
    private DovecoteDailyMapper dovecoteDailyMapper;

    @Override
    public int getAmountOfMatEggs(Long baseId, String dovecoteNumber) {
        int amount = dovecoteDailyMapper.getAmountOfMatEggs(baseId, dovecoteNumber);
        return amount;
    }

    @Override
    public int getAmountOfPictureEggs(Long baseId, String dovecoteNumber) {
        int amount = dovecoteDailyMapper.getAmountOfPictureEggs(baseId, dovecoteNumber);
        return amount;
    }

    @Override
    public int getAmountOfTakeEggs(Long baseId, String dovecoteNumber) {
        int amount = dovecoteDailyMapper.getAmountOfTakeEggs(baseId, dovecoteNumber);
        return amount;
    }

    @Override
    public List<AbnormalVo> getKindAndAmountOfAbnormal(Long baseId, String dovecoteNumber) {
        List<AbnormalVo> abnormalList = dovecoteDailyMapper.getKindAndAmountOfAbnormal(baseId, dovecoteNumber);
        return abnormalList;
    }

    @Override
    public List<DovecoteDaily> getDovecoteDaily(Long baseId, String dovecoteNumber, int year, int month, int day) {
        List<DovecoteDaily> dovecoteDaily = dovecoteDailyMapper.getDovecoteDaily(baseId, dovecoteNumber, year, month, day);
        return dovecoteDaily;
    }


    @Override
    public int updateDovecoteDaily(Long baseId, String dovecoteNumber) {
        int amountOfMatEggs = dovecoteDailyMapper.getAmountOfMatEggs(baseId,dovecoteNumber);
        int amountOfPictureEggs = dovecoteDailyMapper.getAmountOfPictureEggs(baseId,dovecoteNumber);
        int amountOfTakeEggs = dovecoteDailyMapper.getAmountOfTakeEggs(baseId,dovecoteNumber);
        List<AbnormalVo> abnormalVoList = dovecoteDailyMapper.getKindAndAmountOfAbnormal(baseId, dovecoteNumber);
        int singleEggs = 0;
        int unfertilizedEggs = 0;
        int damagedEggs = 0;
        int badEggs = 0;
        for (AbnormalVo abnormalVo : abnormalVoList) {
            //照蛋或抽蛋操作
            if (abnormalVo.getState() != 5){
                if (abnormalVo.getAbnormal() == "1"){
                    singleEggs += abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal() == "2"){
                    unfertilizedEggs += abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal() == "3"){
                    unfertilizedEggs += 2 * abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal() == "4"){
                    damagedEggs += abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal() == "5"){
                    damagedEggs += 2 * abnormalVo.getAmount();
                }else {
                    throw new GlobalException(StatusCode.ERROR,"数据错误");
                }
            }
            if (abnormalVo.getState() == 5){
                if (abnormalVo.getAbnormal().indexOf(0) == '1'){
                    badEggs += abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal().indexOf(1) == '2'){
                    badEggs += 2 * abnormalVo.getAmount();
                }
            }
        }
        DovecoteDaily dovecoteDaily = new DovecoteDaily();
        dovecoteDaily.setBaseId(baseId);
        dovecoteDaily.setDovecoteNumber(dovecoteNumber);
        dovecoteDaily.setMatEggs(amountOfMatEggs);
        dovecoteDaily.setPictureEggs(amountOfPictureEggs);
        dovecoteDaily.setTakeEggs(amountOfTakeEggs);
        dovecoteDaily.setSingleEggs(singleEggs);
        dovecoteDaily.setUnfertilizedEggs(unfertilizedEggs);
        dovecoteDaily.setDamagedEggs(damagedEggs);
        dovecoteDaily.setBadEggs(badEggs);

        int insert = dovecoteDailyMapper.insert(dovecoteDaily);
        return insert;
    }
}
