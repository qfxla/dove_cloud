package com.dove.breed.mapper;

import com.dove.breed.entity.CagePosition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@Mapper
public interface CagePositionMapper extends BaseMapper<CagePosition> {
    Long getCageId(@Param("baseId")Long baseId,
                   @Param("dovecoteNumber")String dovecoteNumber,
                   @Param("rowNo")int row,
                   @Param("line")int line,
                   @Param("columnNo")int column);

    //通过cageId获取位置
    CagePosition getPosition(Long cageId);

//    //产蛋周期排行榜用，获取一个鸽棚的不包括查仔状态的所有cageId
//    List<Long> getAllCageIdOfDovecote(@Param("baseId")Long baseId,@Param("dovecoteNumber")String dovecoteNumber);

    //获取所有cageId
    List<String> getAllCageId();
}
