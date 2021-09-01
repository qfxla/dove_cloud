package com.dove.breed.service;

import com.dove.breed.entity.CagePosition;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
public interface CagePositionService extends IService<CagePosition> {
    Long getCageId(Long baseId, String dovecoteNumber, int rowNo, int line, int columnNo);
}
