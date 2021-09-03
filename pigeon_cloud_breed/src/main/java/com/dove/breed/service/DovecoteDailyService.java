package com.dove.breed.service;

import com.dove.breed.entity.DovecoteDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.AbnormalVo;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Param;

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
    int getAmountOfMatEggs(Long baseId,String dovecoteNumber);
    int getAmountOfPictureEggs(Long baseId,String dovecoteNumber);
    int getAmountOfTakeEggs(Long baseId,String dovecoteNumber);
    List<AbnormalVo> getKindAndAmountOfAbnormal(Long baseId,String dovecoteNumber);
    List<DovecoteDaily> getDovecoteDaily(Long baseId,String dovecoteNumber,
                                         int year,int month,int day);

    int updateDovecoteDaily(Long baseId,String dovecoteNumber);
}
