package com.dove.breed.service;

import com.dove.breed.entity.ManualIncubation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.dto.ManualIncubationDto;
import com.dove.breed.entity.vo.ManualIncubationVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 孵化机 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-09
 */
public interface ManualIncubationService extends IService<ManualIncubation> {

    int addManualIncubationData(ManualIncubationDto manualIncubationDto);

    List<ManualIncubation> getByDovecoteNumber(Long baseId, String dovecoteNumber, int pageNum, int pageSize);

    List<ManualIncubationVo> getByDate(Long baseId, int year, int month, int day);

    void exportDailyData(HttpServletResponse response, Long baseId);

    ManualIncubationVo get7DayOfOneIncubation(Long baseId,String dovecoteNumber);

    List<ManualIncubation> getSevenDay(Long baseId, String dovecoteNumber);
}
