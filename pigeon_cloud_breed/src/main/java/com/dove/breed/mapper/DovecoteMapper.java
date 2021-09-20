package com.dove.breed.mapper;

import com.dove.breed.entity.Dovecote;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.CageRealVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * <p>
 * 鸽棚表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface DovecoteMapper extends BaseMapper<Dovecote> {
    Integer getNeedPictureEggs(@Param("baseId")Long baseId,
                            @Param("dovecoteNumber")String dovecoteNumber);
    Integer getNeedCheckDoves(@Param("baseId")Long baseId,
                              @Param("dovecoteNumber")String dovecoteNumber);
    Integer getNeedTakeEggs(@Param("baseId")Long baseId,
                            @Param("dovecoteNumber")String dovecoteNumber);
    Integer getMatEggsOfYesterday(@Param("baseId")Long baseId,
                                  @Param("dovecoteNumber")String dovecoteNumber);
    List<AbnormalVo> getAbnormalVoOfYesterday(@Param("baseId")Long baseId,
                                              @Param("dovecoteNumber")String dovecoteNumber);

    List<String> getAllDovecoteNumber(@Param("baseId")Long baseId);

    //获取实时表中状态为查完仔的鸽笼
    List<CageRealVo> getCheckEggsToNow(@Param("baseId")Long baseId,
                                       @Param("dovecoteNumber")String dovecoteNumber,
                                       @Param("days")int days);

    //获得异常数最多的前number个鸽笼
    List<CageRealVo> getMaxAbnormal(@Param("baseId")Long baseId,
                                    @Param("dovecoteNumber")String dovecoteNumber,
                                    @Param("number")int number);
}
