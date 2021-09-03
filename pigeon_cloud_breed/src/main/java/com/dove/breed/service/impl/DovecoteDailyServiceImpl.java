package com.dove.breed.service.impl;

import com.dove.breed.entity.DovecoteDaily;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.DovecoteDailyVo;
import com.dove.breed.entity.vo.DovecoteVo;
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


    //获取日结表信息，按天查询
    @Override
    public DovecoteDailyVo getDovecoteDaily(Long baseId, String dovecoteNumber, int year, int month, int day) {
        //查按小时的日结表信息
        List<DovecoteDaily> list = dovecoteDailyMapper.getDovecoteDaily(baseId, dovecoteNumber, year, month, day);
        //封装到按一天的
        DovecoteDailyVo dovecoteDailyVo = new DovecoteDailyVo(baseId,dovecoteNumber,0,0,0,0,0,0,0);
        for (DovecoteDaily po : list) {
            dovecoteDailyVo.setMatEggs(dovecoteDailyVo.getMatEggs() + po.getMatEggs());
            dovecoteDailyVo.setTakeEggs(dovecoteDailyVo.getTakeEggs() + po.getTakeEggs());
            dovecoteDailyVo.setPictureEggs(dovecoteDailyVo.getPictureEggs() + po.getPictureEggs());
            dovecoteDailyVo.setDamagedEggs(dovecoteDailyVo.getDamagedEggs() + po.getDamagedEggs());
            dovecoteDailyVo.setSingleEggs(dovecoteDailyVo.getSingleEggs() + po.getSingleEggs());
            dovecoteDailyVo.setBadEggs(dovecoteDailyVo.getBadEggs() + po.getBadEggs());
            dovecoteDailyVo.setUnfertilizedEggs(dovecoteDailyVo.getUnfertilizedEggs() + po.getUnfertilizedEggs());
        }

        return dovecoteDailyVo;
    }


    //汇总历史表向日结表，按小时汇总
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
