package com.dove.breed.service.impl;

import com.dove.breed.entity.CagePicture;
import com.dove.breed.entity.CageVideo;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.mapper.CagePictureMapper;
import com.dove.breed.mapper.CageVideoMapper;
import com.dove.breed.service.CagePictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 鸽笼图片表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@Service
public class CagePictureServiceImpl extends ServiceImpl<CagePictureMapper, CagePicture> implements CagePictureService {

    @Autowired
    private CagePictureMapper cagePictureMapper;

    @Override
    public void addCagePic(CageRealVo cageRealVo) {
        CagePicture cagePic = cagePictureMapper.getCageVideoByCageId(cageRealVo.getBcNo());
        if (cagePic != null){
            cageRealVo.setPicUrl(cagePic.getPic());
        }else {
            cageRealVo.setPicUrl("");
        }
    }
}
