package com.dove.breed.controller;



import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.dove.breed.entity.dto.BreedBaseDto;
import com.dove.breed.entity.vo.BreedBaseVo;
import com.dove.breed.service.BreedBaseService;
import com.dove.breed.entity.BreedBase;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.GoFastDfsEnum;
import com.dove.entity.Result;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import cn.hutool.core.io.resource.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
* <p>
    * 养殖基地信息表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "养殖基地信息表")
@RestController
@RequestMapping("/breed/Base")
public class BreedBaseController {

    @Autowired
    public BreedBaseService breedBaseService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody BreedBaseDto breedBaseDto){
        BreedBase breedBase = convertUtil.convert(breedBaseDto, BreedBase.class);
        breedBase.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        breedBase.setEnterpriseId(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = breedBaseService.save(breedBase);
        return save? Result.success("保存成功").data(breedBase) : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = breedBaseService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody BreedBaseDto breedBaseDto){
        BreedBase breedBase = convertUtil.convert(breedBaseDto, BreedBase.class);
        breedBase.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        List<BreedBase> breedBaseList = breedBaseService.list(new QueryWrapper<>(breedBase));
        List<BreedBaseVo> breedBasesVoList = convertUtil.convert(breedBaseList,BreedBaseVo.class);
        return breedBaseList.size() > 0?Result.success("查询成功").data(breedBasesVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        QueryWrapper<BreedBase> wrapper = new QueryWrapper<>();
        wrapper.eq("enterprise_id",SecurityContextUtil.getUserDetails().getEnterpriseId());
        IPage<BreedBase> page = breedBaseService.page(new Page<>(pageNum, pageSize), wrapper);
        IPage<BreedBaseVo> page1 = convertUtil.convert(page, BreedBaseVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        BreedBase breedBase = breedBaseService.getById(id);
        BreedBaseVo breedBaseVo = convertUtil.convert(breedBase, BreedBaseVo.class);
        return breedBaseVo != null? Result.success("查询成功").data(breedBaseVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody BreedBaseDto breedBaseDto){
        BreedBase breedBase = convertUtil.convert(breedBaseDto, BreedBase.class);
        breedBase.setId(id);
        boolean b = breedBaseService.updateById(breedBase);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


    @ApiOperation(value = "更新基地照片")
    @PostMapping("/uploadPicture/{id}")
    public Result updatePicture(@PathVariable("id") Long id,
                                @RequestParam("file") MultipartFile files) {
        // 根据id获取基地信息
        BreedBase breedBase = breedBaseService.getById(id);

        if (breedBase == null){
            return Result.error("无该基地信息");
        }

        String path = breedBase.getPicture();

        //文件地址
        InputStreamResource file = null;
        try {
            file = new InputStreamResource(files.getInputStream(), files.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //声明参数集合
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        //文件
        paramMap.put("file", file);
        //输出
        paramMap.put("output","json");
        //场景
        paramMap.put("scene","baseImage");
        //上传
        String result= HttpUtil.post(GoFastDfsEnum.UPLOAD_PATH.getUsr(), paramMap);
        System.out.println("66");
        Map<String,Object> mapType = JSON.parseObject(result,Map.class);
        System.out.println("66");
        for (Object obj : mapType.keySet()){
            if (obj.toString().equals("path")){
                // 修改数据库照片的绝对地址
                breedBase.setPicture(mapType.get(obj).toString());
            }
        }
        boolean b = breedBaseService.updateById(breedBase);
        if (b && path != null && !path.equals(breedBase.getPicture())){
            HashMap<String, Object> pathMap = new HashMap<>();
            pathMap.put("path",path);
            String post = HttpUtil.post(GoFastDfsEnum.DELETE_PATH.getUsr(), pathMap);
            System.out.println(post);
        }
        //输出json结果
        return Result.success("图片保存成功");
    }

    @ApiOperation(value = "更新基地视频")
    @PostMapping("/uploadVideo/{id}")
    public Result uploadVideo(@PathVariable("id") Long id,
                              @RequestParam("url") String url) {
        // 根据id获取基地信息
        BreedBase breedBase = breedBaseService.getById(id);
        breedBase.setVideo(url);
        breedBaseService.updateById(breedBase);
        return Result.success("保存视频成功");
    }
}
