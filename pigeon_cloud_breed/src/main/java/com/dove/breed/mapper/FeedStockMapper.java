package com.dove.breed.mapper;

import com.dove.breed.entity.FeedStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.FeedStockVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 月底饲料盘点表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface FeedStockMapper extends BaseMapper<FeedStock> {
    List<FeedStockVo> getFeedAmountOfMonth(@Param("baseId")Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                          @Param("year")int year, @Param("month")int month);
}
