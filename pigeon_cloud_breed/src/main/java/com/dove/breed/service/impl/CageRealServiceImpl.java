package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.CageReal;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.mapper.CageRealMapper;
import com.dove.breed.service.CageRealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-01
 */
@Service
public class CageRealServiceImpl extends ServiceImpl<CageRealMapper, CageReal> implements CageRealService {
    @Autowired
    private CageRealMapper cageRealMapper;
    @Autowired
    private ConvertUtil convertUtil;

    @Override
    public List<CageRealVo> getAllCage(Long baseId, String dovecoteNumber) {
        List<CageRealVo> allCage = cageRealMapper.getAllCage(baseId, dovecoteNumber);
        for (CageRealVo cageRealVo : allCage) {
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return allCage;
    }

    @Override
    public List<CageRealVo> getLayEggsTime(Long baseId, String dovecoteNumber) {
        List<CageRealVo> layEggsTime = cageRealMapper.getLayEggsTime(baseId, dovecoteNumber);
        for (CageRealVo cageRealVo : layEggsTime) {
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return layEggsTime;
    }

    @Override
    public List<CageRealVo> getHatchTime(Long baseId, String dovecoteNumber) {
        List<CageRealVo> hatchTime = cageRealMapper.getHatchTime(baseId, dovecoteNumber);
        for (CageRealVo cageRealVo : hatchTime) {
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return hatchTime;
    }

    @Override
    public List<CageRealVo> getFeedTime(Long baseId, String dovecoteNumber) {
        List<CageRealVo> feedTime = cageRealMapper.getFeedTime(baseId, dovecoteNumber);
        for (CageRealVo cageRealVo : feedTime) {
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return feedTime;
    }

    /*
    * page对应的record添加异常数据*/
    @Override
    public Page<CageRealVo> addAbnormal(Page<CageRealVo> page) {
        List<CageRealVo> records = page.getRecords();
        List<CageRealVo> recordsNew = new ArrayList<>(records.size());
        for (CageRealVo record : records) {
            List<CageRealVo> cageRealVos = cageRealMapper.addAbnormalData(record.getCageId());
            int totalAbnormal = 0;
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
                        System.out.println(cageRealVo.getAbnormal().indexOf(0));
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
            totalAbnormal = singleEggs + oneUnfertilizedEggs + twoUnfertilizedEggs + twoBadEggs + oneBadEggs + oneDamagedEggs + twoDamagedEggs;
            record.setTotalAbnormal(totalAbnormal);
            record.setOneBad(oneBadEggs);
            record.setTwoBad(twoBadEggs);
            record.setOneDamaged(oneDamagedEggs);
            record.setTwoDamaged(twoDamagedEggs);
            record.setOneUnfertilized(oneUnfertilizedEggs);
            record.setTwoUnfertilized(twoUnfertilizedEggs);
            record.setSingleEgg(singleEggs);
            recordsNew.add(record);
        }
        page.setRecords(recordsNew);
        return page;
    }


}
