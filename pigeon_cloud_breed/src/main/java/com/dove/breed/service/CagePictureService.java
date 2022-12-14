package com.dove.breed.service;

import com.dove.breed.entity.CagePicture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.CageRealVo;

/**
 * <p>
 * 鸽笼图片表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
public interface CagePictureService extends IService<CagePicture> {
    void addCagePic(CageRealVo cageRealVo);
}
