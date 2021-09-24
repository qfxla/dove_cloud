package com.dove.breed.service;

import com.dove.breed.entity.Dovecote;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.CageRealVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 鸽棚表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */

public interface DovecoteService extends IService<Dovecote> {
    Integer getNeedPictureEggs(Long baseId,String dovecoteNumber);
    Integer getNeedCheckDoves(Long baseId,String dovecoteNumber);
    Integer getNeedTakeEggs(Long baseId,String dovecoteNumber);
    Integer getMatEggsOfYesterday(Long baseId,String dovecoteNumber);
    List<AbnormalVo> getAbnormalVoOfYesterday(Long baseId,String dovecoteNumber);

    List<String> getAllDovecoteNumber(Long baseId);

    List<CageRealVo> rightByDays(Long baseId, String dovecoteNumber, int days);

    List<CageRealVo> getMaxAbnormal(Long baseId,String dovecoteNumber,int number);

    Map<String,Integer> getAmountOfState(Long baseId,String dovecoteNumber);

    List<CageRealVo> getBestLayEggCycle(Long baseId,String dovecoteNumber,int number);


}
