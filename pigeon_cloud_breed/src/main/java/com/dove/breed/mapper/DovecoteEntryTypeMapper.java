package com.dove.breed.mapper;

import com.dove.breed.entity.DovecoteEntryType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 鸽棚入仓类型表 Mapper 接口
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Mapper
public interface DovecoteEntryTypeMapper extends BaseMapper<DovecoteEntryType> {

    /**
     * 获取饲料名字
     * @return
     */
    List<String> getFeedName();

    /**
     * 通过名字，类型和规格来查询id
     * @param typeName
     * @param specifications
     * @return
     */
    Long getTypeIdByNameAndSpecificationsAndType(@Param("typeName") String typeName,@Param("specifications") String specifications);

    /**
     * 获取饲料规格
     * @param feedName
     * @return
     */
    List<String> getFeedSpecificationsByName(@Param("feedName") String feedName);

    /**
     * 根据产品编号查询产品规格
     * @param typeId
     * @return
     */
    String getFeedSpecificationById(@Param("typeId") Long typeId);
}
