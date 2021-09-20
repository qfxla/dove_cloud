package com.dove.breed.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.CageReal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.CageRealVo;
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
    List<CageRealVo> getAllCage(Long baseId, String dovecoteNumber);
    List<CageRealVo> getLayEggsTime(Long baseId,String dovecoteNumber);
    List<CageRealVo> getHatchTime(Long baseId,String dovecoteNumber);
    List<CageRealVo> getFeedTime(Long baseId,String dovecoteNumber);
    Page<CageRealVo> addAbnormal(Page<CageRealVo> page);

}
