package com.dove.breed.service;

import com.dove.breed.entity.CageVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.CageRealVo;

/**
 * <p>
 * 鸽笼视频表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-28
 */
public interface CageVideoService extends IService<CageVideo> {
    void addCageVideo(CageRealVo cageRealVo);
}
