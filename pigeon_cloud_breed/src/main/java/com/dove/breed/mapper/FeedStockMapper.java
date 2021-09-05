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

    /**
     * 得到上个月的剩余饲料
     * @param lastMonthTime
     * @return
     */
    Integer getLastMonthFeed(@Param("lastMonthTime")String lastMonthTime);

    /**
     * 确定当月是否有相同规格饲料和名字的饲料月结
     * @param baseId
     * @param dovecoteNumber
     * @param typeName
     * @param specifications
     * @return
     */
    FeedStock getNowMonthByTypeAndSpecifications(@Param("baseId")Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                                 @Param("typeName")String typeName, @Param("specifications")String specifications);

    /**
     * 获取月结报表
     * @param baseId
     * @param dovecoteNumber
     * @param feedType
     * @param month
     * @return
     */
    List<FeedStockVo> getMonthlyStatementReport(@Param("baseId")Long baseId, @Param("dovecoteNumber")String dovecoteNumber,
                                                @Param("feedType")String feedType,@Param("month")String month);
}
