package com.dove.breed.mapper;

import com.dove.breed.entity.CagePicture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 鸽笼图片表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@Mapper
public interface CagePictureMapper extends BaseMapper<CagePicture> {
    //根据cageId获取前一天的图片路径
    List<String> getYesterdayUrl(@Param("cageId")String cageId);

    CagePicture getCageVideoByCageId(@Param("cageId") String cageId);
}
