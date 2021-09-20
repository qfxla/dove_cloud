package com.dove.breed.service.impl;

import com.dove.breed.entity.Dovecote;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.mapper.CageRealMapper;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.service.DovecoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * <p>
 * 鸽棚表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class DovecoteServiceImpl extends ServiceImpl<DovecoteMapper, Dovecote> implements DovecoteService {
    @Resource
    private DovecoteMapper dovecoteMapper;
    @Autowired
    private CageRealMapper cageRealMapper;

    @Override
    public Integer getNeedPictureEggs(Long baseId, String dovecoteNumber) {
        return dovecoteMapper.getNeedPictureEggs(baseId,dovecoteNumber);
    }

    @Override
    public Integer getNeedCheckDoves(Long baseId, String dovecoteNumber) {
        return dovecoteMapper.getNeedCheckDoves(baseId,dovecoteNumber);
    }

    @Override
    public Integer getNeedTakeEggs(Long baseId, String dovecoteNumber) {
        return dovecoteMapper.getNeedTakeEggs(baseId,dovecoteNumber);
    }

    @Override
    public Integer getMatEggsOfYesterday(Long baseId, String dovecoteNumber) {
        int amount = dovecoteMapper.getMatEggsOfYesterday(baseId, dovecoteNumber);
        return amount;
    }

    @Override
    public List<AbnormalVo> getAbnormalVoOfYesterday(Long baseId, String dovecoteNumber) {
        List<AbnormalVo> AbnormalVoList = dovecoteMapper.getAbnormalVoOfYesterday(baseId, dovecoteNumber);
        return AbnormalVoList;
    }

    @Override
    public List<String> getAllDovecoteNumber(Long baseId) {

        return dovecoteMapper.getAllDovecoteNumber(baseId);
    }

    @Override
    public List<CageRealVo> rightByDays(Long baseId, String dovecoteNumber, int days){
        //查找离查仔过来days天的cageId
        List<CageRealVo> list = dovecoteMapper.getCheckEggsToNow(baseId, dovecoteNumber, days);
        for (CageRealVo cageRealVo : list) {
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return list;
    }

    @Override
    public List<CageRealVo> getMaxAbnormal(Long baseId, String dovecoteNumber, int number) {
        //获取异常数最多的前number个鸽笼（cageId和TotalAbnormal）
        List<CageRealVo> cageRealVoList = dovecoteMapper.getMaxAbnormal(baseId, dovecoteNumber, number);
        for (CageRealVo cageRealVo1 : cageRealVoList) {
            List<CageRealVo> cageRealVos = cageRealMapper.addAbnormalData(cageRealVo1.getCageId());
            int singleEggs = 0;
            int oneUnfertilizedEggs = 0;
            int twoUnfertilizedEggs = 0;
            int oneDamagedEggs = 0;
            int twoDamagedEggs = 0;
            int oneBadEggs = 0;
            int twoBadEggs = 0;
            for (CageRealVo cageRealVo : cageRealVos) {
                //照蛋或抽蛋操作
                if (cageRealVo.getState() != 5){
                    if (cageRealVo.getAbnormal().charAt(0) == '1'){
                        singleEggs++;
                    }else if (cageRealVo.getAbnormal().charAt(0)  == '2'){
                        oneUnfertilizedEggs++;
                    }else if (cageRealVo.getAbnormal().charAt(0)  == '3'){
                        twoUnfertilizedEggs++;
                    }else if (cageRealVo.getAbnormal().charAt(0)  == '4'){
                        oneDamagedEggs++;
                    }else if (cageRealVo.getAbnormal().charAt(0)  == '5'){
                        twoDamagedEggs++;
                    }else {
                        throw new GlobalException(StatusCode.ERROR,"数据错误");
                    }
                }
                if (cageRealVo.getState() == 5){
                    if (cageRealVo.getAbnormal().charAt(0)  == '1'){
                        oneBadEggs++;
                    }else if (cageRealVo.getAbnormal().charAt(0)  == '2'){
                        twoBadEggs++;
                    }
                }
            }
            cageRealVo1.setSingleEgg(singleEggs);
            cageRealVo1.setOneBad(oneBadEggs);
            cageRealVo1.setTwoBad(twoBadEggs);
            cageRealVo1.setOneDamaged(oneDamagedEggs);
            cageRealVo1.setTwoDamaged(twoDamagedEggs);
            cageRealVo1.setOneUnfertilized(oneUnfertilizedEggs);
            cageRealVo1.setTwoUnfertilized(twoUnfertilizedEggs);
        }
        //添加position
        for (CageRealVo cageRealVo : cageRealVoList) {

        }
        return cageRealVoList;
    }


}
