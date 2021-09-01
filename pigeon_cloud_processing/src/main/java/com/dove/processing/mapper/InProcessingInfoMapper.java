package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.InProcessingInfo;
import com.dove.processing.entity.Vo.InProcessingBothVo;
import com.dove.processing.entity.Vo.InProcessingInfoBindBillVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 加工厂入库信息表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */

public interface InProcessingInfoMapper extends BaseMapper<InProcessingInfo> {


    Integer batchDeleteByIds(@Param("ids") ArrayList<Long> ids);

    List<InProcessingInfoBindBillVo> getInProcessByPage(@Param("no")int no , @Param("size") int size);

    List<InProcessingInfoBindBillVo> getDetailsInfoByInId(@Param("id") Long id);

    List<InProcessingInfoBindBillVo> getInfosBySearch(@Param("value") String value,@Param("no")int no , @Param("size") int size);

    List<InProcessingInfoBindBillVo> getDataByDateStamp(@Param("no") int no, @Param("size") int size, @Param("firstTime")String firstTime,@Param("lastTime")String lastTime);
}
