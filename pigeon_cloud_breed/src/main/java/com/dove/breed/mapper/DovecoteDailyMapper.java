package com.dove.breed.mapper;

import com.dove.breed.entity.DovecoteDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.DovecoteDailyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 鸽棚日结表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-02
 */
@Mapper
public interface DovecoteDailyMapper extends BaseMapper<DovecoteDaily> {
    //查询信息存入日结表用
    int getAmountOfMatEggs(@Param("baseId")Long baseId,
                            @Param("dovecoteNumber")String dovecoteNumber);
    int getAmountOfPictureEggs(@Param("baseId")Long baseId,
                                @Param("dovecoteNumber")String dovecoteNumber);
    int getAmountOfTakeEggs(@Param("baseId")Long baseId,
                             @Param("dovecoteNumber")String dovecoteNumber);
    List<AbnormalVo> getKindAndAmountOfAbnormal(@Param("baseId")Long baseId,
                                                @Param("dovecoteNumber")String dovecoteNumber);

    //正常
    DovecoteDaily getDovecoteDaily(@Param("baseId")Long baseId,@Param("dovecoteNumber")String dovecoteNumber,
                                         @Param("year")int year,@Param("month")int month,@Param("day")int day);
    List<DovecoteDailyVo> getAllDovecoteDaily(@Param("baseId")Long baseId, @Param("year")int year,
                                              @Param("month")int month, @Param("day")int day);
    Date getUpdateTime();

    List<DovecoteDaily> getToExcel(@Param("baseId")Long baseId);

    DovecoteDaily get7DayOfOneDovecote(@Param("baseId") Long baseId,@Param("dovecoteNumber") String dovecoteNumber);

    List<DovecoteDaily> getDataOf7Day(@Param("baseId") Long baseId,@Param("dovecoteNumber") String dovecoteNumber);
}
