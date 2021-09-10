package com.dove.breed.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.Dovecote;
import com.dove.breed.entity.DovecoteDaily;
import com.dove.breed.entity.ManualIncubation;
import com.dove.breed.entity.dto.ManualIncubationDto;
import com.dove.breed.entity.vo.DovecoteDailyVo;
import com.dove.breed.entity.vo.ManualIncubationVo;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.mapper.ManualIncubationMapper;
import com.dove.breed.service.ManualIncubationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import org.bouncycastle.jcajce.provider.symmetric.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.nio.channels.WritePendingException;
import java.util.List;

/**
 * <p>
 * 孵化机 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-09
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
    public int addManualIncubationData(ManualIncubationDto manualIncubationDto) {
        //根据鸽棚查breeder
        QueryWrapper<Dovecote> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id",manualIncubationDto.getBaseId()).
                eq("dovecote_number",manualIncubationDto.getDovecoteNumber());
        List<Dovecote> dovecotes = dovecoteMapper.selectList(wrapper);
        String breeder = "";
        if (dovecotes.size() != 0){
            breeder = dovecotes.get(0).getDirector();
        }
        ManualIncubation manualIncubation = convertUtil.convert(manualIncubationDto, ManualIncubation.class);
        manualIncubation.setBreeder(breeder);

        List<ManualIncubation> todayData = manualIncubationMapper.getTodayData(manualIncubation.getBaseId(), manualIncubation.getDovecoteNumber());
        if (todayData.size()>2){
            return 0;
        }
        int insert = manualIncubationMapper.insert(manualIncubation);
        return insert>0? 1:0;
    }

    @Override
    public List<ManualIncubation> getByDovecoteNumber(Long baseId, String dovecoteNumber, int pageNum, int pageSize) {
        List<ManualIncubation> list = manualIncubationMapper.getByDovecoteNumber(baseId, dovecoteNumber);
        return list;
    }

    @Override
    public List<ManualIncubationVo> getByDate(Long baseId, int year, int month, int day) {
        List<ManualIncubation> byDate = manualIncubationMapper.getByDate(baseId, year, month, day);
        List<ManualIncubationVo> manualIncubationVoList = convertUtil.convert(byDate, ManualIncubationVo.class);
        return manualIncubationVoList;
    }

    @Override
    public void exportDailyData(HttpServletResponse response, Long baseId) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //这里URLEncoder.encode可以防止中文乱码，当然和easyexcel没有关系
        String fileName = "孵化机记录";
        response.setHeader("Content-disposition","attachment;filename=" + fileName + ".xlsx");
        //查询数据库
        QueryWrapper<ManualIncubation> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id",baseId).eq("is_deleted",0);
        List<ManualIncubation> list = manualIncubationService.list(wrapper);
        List<ManualIncubationVo> list1 = convertUtil.convert(list, ManualIncubationVo.class);
        //调用方法进行写操作
        try{
            EasyExcel.write(response.getOutputStream(),ManualIncubationVo.class).sheet("孵化机记录")
                    .doWrite(list1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
