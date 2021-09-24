package com.dove.breed.service.impl;

import com.dove.breed.entity.Picture;
import com.dove.breed.entity.vo.PictureVo;
import com.dove.breed.mapper.PictureMapper;
import com.dove.breed.service.PictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文件地址表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-20
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {

    @Resource
    private PictureMapper pictureMapper;

    @Override
    public List<PictureVo> getBreedPicture(Long baseId, String dovecoteNumber, Long guige) {
        return pictureMapper.getBreedPicture(baseId,dovecoteNumber,guige);
    }
}