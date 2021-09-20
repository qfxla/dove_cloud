package com.dove.breed.controller;
import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.BreedBase;
import com.dove.breed.entity.DovecoteEntryBill;
import com.dove.breed.entity.dto.DovecoteDto;
import com.dove.breed.entity.vo.AbnormalVo;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.entity.vo.DovecoteVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.GoFastDfsEnum;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;


import com.dove.breed.service.DovecoteService;
import com.dove.breed.entity.Dovecote;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
* <p>
    * 鸽棚表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "鸽棚表")
@RestController
@RequestMapping("/breed/dovecote")
public class DovecoteController {

    @Resource
    public DovecoteService dovecoteService;

    @Resource
    private ConvertUtil convertUtil;

    private String pathPicture = "/dev/img/";
    private String pathVideo = "/dev/video/";

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DovecoteDto dovecoteDto){
        Dovecote dovecote = convertUtil.convert(dovecoteDto, Dovecote.class);
        boolean save = dovecoteService.save(dovecote);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = dovecoteService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody DovecoteDto dovecoteDto){
        Dovecote dovecote = convertUtil.convert(dovecoteDto, Dovecote.class);
        List<Dovecote> dovecoteList = dovecoteService.list(new QueryWrapper<>(dovecote));
        List<DovecoteVo> dovecoteVoList = convertUtil.convert(dovecoteList, DovecoteVo.class);
        return dovecoteVoList.size() > 0?Result.success("查询成功").data(dovecoteVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize,
                       @RequestParam("baseId")Long baseId){
        QueryWrapper<Dovecote> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id",baseId);
        List<Dovecote> list = dovecoteService.list(wrapper);
        Page<Dovecote> page1 = PageUtil.list2Page(list,pageNum,pageSize);
        return Result.success("分页成功").data(page1);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        Dovecote dovecote = dovecoteService.getById(id);
        DovecoteVo dovecoteVo = convertUtil.convert(dovecote, DovecoteVo.class);
        return dovecoteVo != null? Result.success("查询成功").data(dovecoteVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody DovecoteDto dovecoteDto){
        Dovecote dovecote = convertUtil.convert(dovecoteDto, Dovecote.class);
        dovecote.setDovecoteId(id);
        boolean b = dovecoteService.updateById(dovecote);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "今日需要照蛋数量")
    @GetMapping("/getNeedPictureEgg")
    public Result getNeedPictureEgg(@RequestParam(value = "baseId")Long baseId,
                              @RequestParam(value = "dovecoteNumber")String dovecoteNumber){
        return Result.success("查找成功").data(dovecoteService.getNeedPictureEggs(baseId, dovecoteNumber));
    }

    @ApiOperation(value = "今日需要查仔数量")
    @GetMapping("/getNeedCheckDoves")
    public Result getNeedCheckDoves(@RequestParam(value = "baseId")Long baseId,
                                    @RequestParam(value = "dovecoteNumber")String dovecoteNumber){
        return Result.success("查找成功").data(dovecoteService.getNeedCheckDoves(baseId, dovecoteNumber));
    }

    @ApiOperation(value = "今天需要抽蛋数量")
    @GetMapping("/getNeedTakeEggs")
    public Result getNeedTakeEggs(@RequestParam(value = "baseId")Long baseId,
                                  @RequestParam(value = "dovecoteNumber")String dovecoteNumber){
        return Result.success("查找成功").data(dovecoteService.getNeedTakeEggs(baseId, dovecoteNumber));
    }

    @ApiOperation(value = "昨日垫蛋数")
    @GetMapping("/getMatEggsOfYesterday")
    public Result getMatEggsOfYesterday(@RequestParam(value = "baseId")Long baseId,
                                        @RequestParam(value = "dovecoteNumber")String dovecoteNumber){
        return Result.success("查找成功").data(dovecoteService.getMatEggsOfYesterday(baseId, dovecoteNumber));
    }

    @ApiOperation(value = "昨日异常统计")
    @GetMapping("/getAbnormalVoOfYesterday")
    public Result getAbnormalVoOfYesterday(@RequestParam(value = "baseId")Long baseId,
                                           @RequestParam(value = "dovecoteNumber")String dovecoteNumber){
        return Result.success("查找成功").data(dovecoteService.getAbnormalVoOfYesterday(baseId, dovecoteNumber));
    }

    @ApiOperation(value = "根据基地查询出所有鸽棚编号")
    @GetMapping("/getAllDovecoteNumber")
    public Result getAllDovecoteNumber(@RequestParam(value = "baseId")Long baseId){
        return Result.success("查找成功").data(dovecoteService.getAllDovecoteNumber(baseId));
    }

    @ApiOperation(value = "亮里查仔过来几天的灯")
    @GetMapping("/rightByDays")
    public Result rightByDays(@RequestParam(value = "baseId")Long baseId,
                              @RequestParam(value = "dovecoteNumber")String dovecoteNumber,
                              @RequestParam(value = "days")int days){
        List<CageRealVo> list = dovecoteService.rightByDays(baseId, dovecoteNumber, days);
        return Result.success("获取成功").data(list);
    }

    @ApiOperation(value = "更新鸽棚照片")
    @PostMapping("/uploadPicture/{id}")
    public Result updatePicture(@PathVariable("id") Long id,
                                @RequestParam("file") MultipartFile files) {
        // 根据id获取基地信息
        Dovecote dovecote = dovecoteService.getById(id);

        if (dovecote == null){
            return Result.error("无该鸽棚信息");
        }

        String path = dovecote.getPicture();

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
        //自定义路径
        paramMap.put("path","dovecoteImage");
        //场景
        paramMap.put("scene","dovecoteImage");
        //上传
        String result= HttpUtil.post(GoFastDfsEnum.UPLOAD_PATH.getUsr(), paramMap);
        Map<String,Object> mapType = JSON.parseObject(result,Map.class);
        for (Object obj : mapType.keySet()){
            if (obj.toString().equals("path")){
                // 修改数据库照片的绝对地址
                dovecote.setPicture(mapType.get(obj).toString());
            }
        }
        boolean b = dovecoteService.updateById(dovecote);
        if (b && path != null && !path.equals(dovecote.getPicture())){
            HashMap<String, Object> pathMap = new HashMap<>();
            pathMap.put("path",path);
            HttpUtil.post(GoFastDfsEnum.DELETE_PATH.getUsr(), pathMap);
        }
        //输出json结果
        return Result.success("图片保存成功");
    }

    @ApiOperation(value = "更新鸽棚视频")
    @PostMapping("/uploadVideo/{id}")
    public Result uploadVideo(@PathVariable("id") Long id,
                              @RequestParam("url") String url) {
        // 根据id获取基地信息
        Dovecote dovecote = dovecoteService.getById(id);
        dovecote.setVideo(url);
        dovecoteService.updateById(dovecote);
        return Result.success("保存视频成功");
    }
}
