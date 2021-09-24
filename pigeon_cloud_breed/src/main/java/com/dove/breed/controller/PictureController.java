package com.dove.breed.controller;


import com.dove.breed.entity.Dovecote;
import com.dove.breed.entity.vo.DovecoteVo;
import com.dove.breed.service.PictureService;
import com.dove.entity.Result;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 文件地址表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-20
 */
@CrossOrigin
@Slf4j
@Api(tags = "文件表")
@RestController
@RequestMapping("/breed/picture")
public class PictureController {

    @Resource
    private PictureService pictureService;

    @ApiOperation(value = "获取鸽棚照片")
    @GetMapping("/getBreedPicture")
    public Result getBreedPicture(@RequestParam(value = "baseId")Long baseId,
                                  @RequestParam(value = "dovecoteNumber")String dovecoteNumber){
        Long guige = SecurityContextUtil.getUserDetails().getEnterpriseId();
        return Result.success("查询成功").data(pictureService.getBreedPicture(baseId,dovecoteNumber, guige));
    }
}