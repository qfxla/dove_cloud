package com.dove.breed.service.impl;

import com.dove.breed.entity.CagePosition;
import com.dove.breed.entity.Dovecote;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.entity.vo.DoveAmountOfStateVo;
import com.dove.breed.mapper.CagePositionMapper;
import com.dove.breed.mapper.CageRealMapper;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.service.CageService;
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
import java.lang.annotation.ElementType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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
    @Autowired
    private CagePositionMapper cagePositionMapper;
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private CageService cageService;

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
    public List<CageRealVo> getMaxAbnormal(Long baseId, String dovecoteNumber,int pageNum,int pageSize) throws InterruptedException {
        int start = pageSize * (pageNum - 1);
        //获取异常数最多的前number个鸽笼（cageId和TotalAbnormal）
        List<CageRealVo> cageRealVoList = dovecoteMapper.getMaxAbnormal(baseId, dovecoteNumber,start, pageSize);
        System.out.println("1111");
        addAbnormal(cageRealVoList);
        addPosition(cageRealVoList);
//        Future<List<CageRealVo>> future1 = executorService.submit(() -> addAbnormal(cageRealVoList));
//        Future<List<CageRealVo>> future2 = executorService.submit(() -> addPosition(cageRealVoList));
//        while (!(future1.isDone() && future2.isDone())){
//            Thread.sleep(300);
//        }
//        executorService.shutdown();
        return cageRealVoList;
    }

    @Override
    public Map<String, Integer> getAmountOfState(Long baseId, String dovecoteNumber) {
        List<DoveAmountOfStateVo> list = dovecoteMapper.getAmountOfState(baseId, dovecoteNumber);
        Map<String, Integer> map = new HashMap<>(16, 0.75f);
        String s1 = "无蛋";
        map.put(s1,0);
        String s2 = "将照蛋";
        map.put(s2,0);
        String s3 = "将抽蛋";
        map.put(s3,0);
        String s4 = "已照蛋";
        map.put(s4,0);
        String s5 = "已抽蛋";
        map.put(s5,0);
        String s6 = "已查仔";
        map.put(s6,0);
        for (DoveAmountOfStateVo po : list) {
            if (po.getState() == 0){
                map.put(s1,po.getAmount());
            }else if (po.getState() == 1) {
                map.put(s2, po.getAmount());
            }else if (po.getState() == 2){
                map.put(s3,po.getAmount());
            }else if (po.getState() == 3){
                map.put(s5,po.getAmount());
            }else if (po.getState() == 4){
                map.put(s4,po.getAmount());
            }else if (po.getState() == 5){
                map.put(s6,po.getAmount());
            }else {
                throw new GlobalException(StatusCode.ERROR,"鸽子板数据错误");
            }
        }

        return map;
    }

    //获取产蛋周期最少的前number个鸽笼（cageId和LayEggCycle）
    @Override
    public List<CageRealVo> getBestLayEggCycle(Long baseId, String dovecoteNumber,int number) {
//        //获得该鸽棚的非查仔状态的所有cageId
//        List<Long> longList = cagePositionMapper.getAllCageIdOfDovecote(baseId, dovecoteNumber);
//        ArrayList<CageRealVo> cageRealVos = new ArrayList<>();
//        for (Long aLong :longList){
//            int cycle = cageService.getLayEggsCycleByCageId(aLong);
//            CageRealVo cageRealVo = new CageRealVo();
//            cageRealVo.setCageId(aLong);
//            cageRealVo.setLayEggCycle(cycle);
//        }
//        cageRealVos.stream().sorted((u1,u2) -> {return String.valueOf(u1.getLayEggCycle()).compareTo(String.valueOf(u2.getLayEggCycle()));});
//        System.out.println(cageRealVos);
        return null;
    }


    //添加异常
    public List<CageRealVo> addAbnormal(List<CageRealVo> cageRealVoList){
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
        return cageRealVoList;
    }

    //添加position
    public List<CageRealVo> addPosition(List<CageRealVo> cageRealVoList){
        for (CageRealVo cageRealVo : cageRealVoList) {
            CagePosition cagePosition = cagePositionMapper.getPosition(cageRealVo.getCageId());
            int row = cagePosition.getRowNo();
            int line = cagePosition.getLine();
            int column = cagePosition.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageRealVo.setPosition(position);
        }
        return cageRealVoList;
    }
}
