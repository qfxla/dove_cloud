package com.dove.breed.mapper;

import com.dove.breed.entity.Picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.breed.entity.vo.PictureVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文件地址表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-09-20
 */
public interface PictureMapper extends BaseMapper<Picture> {

    List<PictureVo> getBreedPicture(@Param("baseId") Long baseId,
                                    @Param("dovecoteNumber") String dovecoteNumber,
                                    @Param("guige") Long guige);
}