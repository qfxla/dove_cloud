package com.dove.breed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.FeedMachine;
import com.dove.breed.entity.vo.FeedMachineVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 投喂信息表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
public interface FeedMachineMapper extends BaseMapper<FeedMachine> {

    List<FeedMachineVo> listByType(@Param("baseId") Long baseId, @Param("dovecoteNumber") String dovecoteNumber,
                                   @Param("open")Integer open, @Param("enterpriseId") Long enterpriseId);
}
