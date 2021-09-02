package com.dove.breed.service;

import com.dove.breed.entity.Dovecote;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.AbnormalVo;
import org.apache.ibatis.annotations.Param;

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
    Integer getMatEggsOfYesterday(Long baseId,String dovecoteNumber);
    List<AbnormalVo> getAbnormalVoOfYesterday(Long baseId,String dovecoteNumber);
    Integer getNeedPictureEgg(Long baseId,String dovecoteNumber);
    Integer getNeedCheckDoves(Long baseId,String dovecoteNumber);
}
