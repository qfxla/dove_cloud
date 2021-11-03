package com.dove.breed.mapper;

import com.dove.breed.entity.Operator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.ClearSoilVo;
import com.dove.breed.entity.vo.OperatorVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-11-03
 */
@Mapper
public interface OperatorMapper extends BaseMapper<Operator> {


    List<OperatorVo> listByType(@Param("baseId") Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                @Param("name")String name, @Param("enterpriseId")Long enterpriseId);

    List<String> comboBox(@Param("baseId")Long baseId,
                          @Param("dovecoteNumber")String dovecoteNumber,
                          @Param("enterpriseId") Long enterpriseId);
}
