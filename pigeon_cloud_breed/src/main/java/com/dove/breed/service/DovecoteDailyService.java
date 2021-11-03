package com.dove.breed.service;

import com.alibaba.fastjson.JSONObject;
import com.dove.breed.entity.DovecoteDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.DovecoteDailyVo;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 鸽棚日结表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-02
 */
public interface DovecoteDailyService extends IService<DovecoteDaily> {
    DovecoteDailyVo getDovecoteDaily(Long baseId, String dovecoteNumber,
                                     int year, int month, int day);

    int updateDovecoteDaily(Long baseId,String dovecoteNumber);

    List<DovecoteDailyVo> getAllDovecoteDaily(Long baseId,int year,int month,int day);

    void exportDailyData(HttpServletResponse response,Long baseId);

    void importDictData(MultipartFile file);

    DovecoteDaily get7DayOfOneDovecote(Long baseId, String dovecoteNumber);

    List<DovecoteDaily> getDataOf7Day(Long baseId,String dovecoteNumber);

    List<JSONObject> getDoveAbnormal();
}
