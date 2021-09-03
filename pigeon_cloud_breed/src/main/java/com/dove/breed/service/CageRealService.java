package com.dove.breed.service;

import com.dove.breed.entity.CageReal;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-01
 */
public interface CageRealService extends IService<CageReal> {
    List<CageReal> getAllCage(Long baseId,String dovecoteNumber);
}
