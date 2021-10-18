package com.dove.breed.service.impl;

import com.dove.breed.entity.CageVideo;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.mapper.CageVideoMapper;
import com.dove.breed.service.CageVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 鸽笼视频表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-28
 */
@Service
public class CageVideoServiceImpl extends ServiceImpl<CageVideoMapper, CageVideo> implements CageVideoService {
    @Autowired
    private CageVideoMapper cageVideoMapper;

    @Override
    public void addCageVideo(CageRealVo cageRealVo) {
        CageVideo cageVideo = cageVideoMapper.getCageVideoByCageId(cageRealVo.getCageId());
        if (cageVideo != null){
            cageRealVo.setVideoUrl(cageVideo.getVideo());
        }else {
            cageRealVo.setVideoUrl("");
        }
    }
}
