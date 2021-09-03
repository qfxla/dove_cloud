package com.dove.processing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.processing.entity.BusinessProcessing;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.ProcessingTypeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商家表 Mapper 接口
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */

public interface BusinessProcessingMapper extends BaseMapper<BusinessProcessing> {

    List<BusinessProcessingVo> getBusiInfo(@Param("no") int no,@Param("size") int size);


    List<BusinessProcessingVo> getBusiByLikeSearch(@Param("value") String value,@Param("no") int no,@Param("size") int size);

    long getBusinessProcessingCountByLike(@Param("value") String value);


    List<ProcessingTypeVo> getProductsByPage(@Param("id") Long id,@Param("no")int no,@Param("size")int size);
}
