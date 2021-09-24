package com.dove.breed.service;

import com.dove.breed.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.breed.entity.vo.PictureVo;
import org.aspectj.weaver.ast.Var;

import java.util.List;

/**
 * <p>
 * 文件地址表 服务类
 * </p>
 *
 * @author zcj
 * @since 2021-09-20
 */
public interface PictureService extends IService<Picture> {

    List<PictureVo> getBreedPicture(Long baseId, String dovecoteNumber, Long guige);
}
