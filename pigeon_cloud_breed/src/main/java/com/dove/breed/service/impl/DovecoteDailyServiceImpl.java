package com.dove.breed.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.Dovecote;
import com.dove.breed.entity.DovecoteDaily;
import com.dove.breed.entity.DovecoteOutBase;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.DovecoteDailyVo;
import com.dove.breed.entity.vo.DovecoteVo;
import com.dove.breed.mapper.DovecoteDailyMapper;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.service.DovecoteDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.service.DovecoteService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private DovecoteService dovecoteService;
    @Autowired
    private ConvertUtil convertUtil;


    //获取日结表信息，按天查询
    @Override
    public DovecoteDailyVo getDovecoteDaily(Long baseId, String dovecoteNumber, int year, int month, int day) {
        //查按小时的日结表信息
        DovecoteDaily dovecoteDaily = dovecoteDailyMapper.getDovecoteDaily(baseId, dovecoteNumber, year, month, day);
        DovecoteDailyVo dovecoteDailyVo = convertUtil.convert(dovecoteDaily, DovecoteDailyVo.class);
        return dovecoteDailyVo;
    }


    //汇总历史表向日结表，按天汇总
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
                if (abnormalVo.getAbnormal().charAt(0) == '1'){
                    singleEggs += abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal().charAt(0) == '2'){
                    unfertilizedEggs += abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal().charAt(0) == '3'){
                    unfertilizedEggs += 2 * abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal().charAt(0) == '4'){
                    damagedEggs += abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal().charAt(0) == '5'){
                    damagedEggs += 2 * abnormalVo.getAmount();
                }else {
                    throw new GlobalException(StatusCode.ERROR,"数据错误");
                }
            }
            if (abnormalVo.getState() == 5){
                if (abnormalVo.getAbnormal().charAt(0) == '1'){
                    badEggs += abnormalVo.getAmount();
                }else if (abnormalVo.getAbnormal().charAt(0) == '2'){
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

    @Override
    public List<DovecoteDailyVo> getAllDovecoteDaily(Long baseId, int year, int month, int day) {
        List<DovecoteDailyVo> allDovecoteDaily = dovecoteDailyMapper.getAllDovecoteDaily(baseId, year, month, day);
        return allDovecoteDaily;
    }

    @Override
    public void exportDailyData(HttpServletResponse response,Long baseId) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //这里URLEncoder.encode可以防止中文乱码，当然和easyexcel没有关系
        String fileName = "dovecoteDaily";
        response.setHeader("Content-disposition","attachment;filename=" + fileName + ".xlsx");
        //查询数据库
        List<DovecoteDaily> dovecoteDailies = dovecoteDailyMapper.getToExcel(baseId);
        List<DovecoteDailyVo> dovecoteDailyVoList = convertUtil.convert(dovecoteDailies, DovecoteDailyVo.class);
        //调用方法进行写操作
        try{
            EasyExcel.write(response.getOutputStream(),DovecoteDailyVo.class).sheet("dovecoteDaily")
                    .doWrite(dovecoteDailyVoList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void importDictData(MultipartFile file) {

    }

    @Override
    public DovecoteDaily get7DayOfOneDovecote(Long baseId, String dovecoteNumber) {
        DovecoteDaily dovecoteDaily = dovecoteDailyMapper.get7DayOfOneDovecote(baseId, dovecoteNumber);
        return dovecoteDaily;
    }

    @Override
    public List<DovecoteDaily> getDataOf7Day(Long baseId, String dovecoteNumber) {
        List<DovecoteDaily> list = dovecoteDailyMapper.getDataOf7Day(baseId, dovecoteNumber);
        return list;
    }

    @Override
    public List<JSONObject> getDoveAbnormal(){
        List<Map<String, String>> mapList = dovecoteDailyMapper.getDoveAbnormal();
        List<JSONObject> list = new ArrayList<>(mapList.size());
        for (Map<String, String> map : mapList) {
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jsonObject.put(entry.getKey(),entry.getValue());
            }
            list.add(jsonObject);
        }
        return list;
    }


}





















