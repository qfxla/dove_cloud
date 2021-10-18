package com.dove.breed.mapper;

import com.dove.breed.entity.CageVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 鸽笼视频表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-28
 */
@Mapper
public interface CageVideoMapper extends BaseMapper<CageVideo> {
    CageVideo getCageVideoByCageId(@Param("cageId")Long cageId);
}
