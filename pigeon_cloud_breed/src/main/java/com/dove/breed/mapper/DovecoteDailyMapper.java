package com.dove.breed.mapper;

import com.dove.breed.entity.DovecoteDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.AbnormalVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    int getAmountOfMatEggs(@Param("baseId")Long baseId,
                            @Param("dovecoteNumber")String dovecoteNumber);
    int getAmountOfPictureEggs(@Param("baseId")Long baseId,
                                @Param("dovecoteNumber")String dovecoteNumber);
    int getAmountOfTakeEggs(@Param("baseId")Long baseId,
                             @Param("dovecoteNumber")String dovecoteNumber);
    List<AbnormalVo> getKindAndAmountOfAbnormal(@Param("baseId")Long baseId,
                                                @Param("dovecoteNumber")String dovecoteNumber);
}
