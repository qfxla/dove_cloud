package com.dove.breed.service;

import com.dove.breed.entity.Dovecote;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.AbnormalVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 鸽棚表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */

public interface DovecoteService extends IService<Dovecote> {
    int getMatEggsOfYesterday(Long baseId,String dovecoteNumber);
    List<AbnormalVo> getAbnormalVoOfYesterday(Long baseId,String dovecoteNumber);
    List<Long> getAllCageId(Long baseId,String dovecoteNumber);
    int getCurrentXf(Long cageId);
}
