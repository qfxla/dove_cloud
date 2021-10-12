package com.dove.breed.mapper;

import com.dove.breed.entity.CageReal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.CageRealVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-01
 */
@Mapper
public interface CageRealMapper extends BaseMapper<CageReal> {
    List<CageRealVo> getAllCage(@Param("baseId")Long baseId,
                                @Param("dovecoteNumber")String dovecoteNumber);
    List<CageRealVo> getLayEggsTime(@Param("baseId")Long baseId,
                                  @Param("dovecoteNumber")String dovecoteNumber);
    List<CageRealVo> getHatchTime(@Param("baseId")Long baseId,
                                @Param("dovecoteNumber")String dovecoteNumber);
    List<CageRealVo> getFeedTime(@Param("baseId")Long baseId,
                               @Param("dovecoteNumber")String dovecoteNumber);
    List<CageRealVo> addAbnormalData(@Param("cageId")Long cageId);

    List<CageRealVo> getCageOfDiffState(@Param("baseId")Long baseId,
                                        @Param("dovecoteNumber")String dovecoteNumber,
                                        @Param("state")int state);

    int uiGetLayEggsTimeAmount(@Param("baseId")Long baseId);
    int uiGetHatchTime(@Param("baseId")Long baseId);
    int uiGetFeedTime(@Param("baseId")Long baseId);

}
