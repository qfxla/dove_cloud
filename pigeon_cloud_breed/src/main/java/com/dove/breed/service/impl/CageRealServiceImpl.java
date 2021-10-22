package com.dove.breed.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.CageReal;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.mapper.CageMapper;
import com.dove.breed.mapper.CageRealMapper;
import com.dove.breed.service.CagePictureService;
import com.dove.breed.service.CageRealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.service.CageService;
import com.dove.breed.service.CageVideoService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
    @Autowired
    private CageService cageService;
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private CageVideoService cageVideoService;
    @Autowired
    private CagePictureService cagePictureService;
    @Autowired
    private RedisTemplate redisTemplate;

//    @Override
//    public List<CageRealVo> getAllCage(Long baseId, String dovecoteNumber) {
//        List<CageRealVo> allCage = cageRealMapper.getAllCage(baseId, dovecoteNumber);
//        for (CageRealVo cageRealVo : allCage) {
//            int row = cageRealVo.getRowNo();
//            int line = cageRealVo.getLine();
//            int column = cageRealVo.getColumnNo();
//            String position = row + "排" + line + "行" + column + "列";
//            cageVideoService.addCageVideo(cageRealVo);
//            cagePictureService.addCagePic(cageRealVo);
//            cageRealVo.setPosition(position);
//        }
//        return allCage;
//    }
    @Transactional
    @Override
    public List<CageRealVo> getAllCage(Long baseId, String dovecoteNumber) throws InterruptedException {
        //查redis，如果有直接返回
        String redisPath = "cageReal:getAllCage:" + baseId + ":" + dovecoteNumber;
        List<CageRealVo> list = redisTemplate.opsForList().range(redisPath, 0, -1);
        if (list.size() != 0){
            return list;
        }
        //如果redis没有，先查数据库
        List<CageRealVo> allCage = cageRealMapper.getAllCage(baseId, dovecoteNumber);
        for (CageRealVo cageRealVo : allCage) {                     // 635
            int row = cageRealVo.getRowNo();
            int line = cageRealVo.getLine();
            int column = cageRealVo.getColumnNo();
            String position = row + "排" + line + "行" + column + "列";
            cageVideoService.addCageVideo(cageRealVo);
            cagePictureService.addCagePic(cageRealVo);
            cageRealVo.setPosition(position);
        }
        //存redis
        for (CageRealVo cageRealVo : allCage) {
            redisTemplate.opsForList().leftPush(redisPath,cageRealVo);
            redisTemplate.expire(redisPath,60 * 60 * 24,TimeUnit.SECONDS);  //一天
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
            cageVideoService.addCageVideo(cageRealVo);
            cagePictureService.addCagePic(cageRealVo);
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
            cageVideoService.addCageVideo(cageRealVo);
            cagePictureService.addCagePic(cageRealVo);
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
            cageVideoService.addCageVideo(cageRealVo);
            cagePictureService.addCagePic(cageRealVo);
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
            record.setLayEggCycle(cageService.getLayEggsCycleByCageId(record.getCageId()));
            recordsNew.add(record);
        }
        page.setRecords(recordsNew);
        return page;
    }
    @Override
    public Page<CageRealVo> getCageOfDiffState(Long baseId, String dovecoteNumber, String state,
                                               int pageNum,int pageSize) throws InterruptedException {
        int stateToInt = -1;
        if (state.equals("无蛋")){
            stateToInt = 0;
        }else if (state.equals("将照蛋")){
            stateToInt = 1;
        }else if (state.equals("将抽蛋")){
            stateToInt = 2;
        }else if (state.equals("已照蛋")){
            stateToInt = 4;
        }else if (state.equals("已查仔")){
            stateToInt = 5;
        }else {
            new GlobalException(StatusCode.ERROR,"无该状态，请重新输入！");
        }
        List<CageRealVo> cageRealVoList = cageRealMapper.getCageOfDiffState(baseId, dovecoteNumber, stateToInt);
        Page<CageRealVo> page = PageUtil.list2Page(cageRealVoList, pageNum, pageSize);
        List<CageRealVo> records = page.getRecords();
        List<CageRealVo> recordNew = new ArrayList<>();
        CountDownLatch cdl = new CountDownLatch(records.size());

        //手动添加位置和异常
        for (CageRealVo record : records) {
//            executorService.execute(() -> {
                CageRealVo cageRealVo = addPositionAndAbnormal(record);
            cageVideoService.addCageVideo(cageRealVo);
            cagePictureService.addCagePic(cageRealVo);
                recordNew.add(cageRealVo);
                cdl.countDown();
//            });
        }
        cdl.await();
        executorService.shutdown();
        page.setRecords(recordNew);
        return page;
    }

    public CageRealVo addPositionAndAbnormal(CageRealVo record){
        int row = record.getRowNo();
        int line = record.getLine();
        int column = record.getColumnNo();
        String position = row + "排" + line + "行" + column + "列";
        record.setPosition(position);


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
        record.setLayEggCycle(cageService.getLayEggsCycleByCageId(record.getCageId()));
        return record;
    }

}
