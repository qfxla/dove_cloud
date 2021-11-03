package com.dove.breed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.FeedHistory;
import com.dove.breed.entity.vo.FeedHistoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-22-11:20
 */
public interface FeedHistoryMapper extends BaseMapper<FeedHistory> {

    List<FeedHistoryVo> listByType(@Param("baseId") Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                   @Param("feedNumber") String feedNumber, @Param("operator") String operator,
                                   @Param("startTime")String startTime, @Param("endTime")String endTime,
                                   @Param("enterpriseId")Long enterpriseId);

}