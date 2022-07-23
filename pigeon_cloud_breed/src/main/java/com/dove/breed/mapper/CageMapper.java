package com.dove.breed.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.dove.breed.entity.Cage;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@Mapper
public interface CageMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<Cage> {
    Date getLastOneLayEggsByCageId(@Param("cageId")Long cageId);
    Date getLastTwoLayEggsByCageId(@Param("cageId")Long cageId);
    List<JSONObject> exportData();
}
