package com.dove.breed.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.CageReal;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.entity.vo.DovecoteDailyVo;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.service.CageRealService;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-01
 */
@Api(tags = "鸽笼实时表")
@RestController
@RequestMapping("/breed/cage-real")
public class CageRealController {

    @Autowired
    private CageRealService cageRealService;

    @ApiOperation("获取所有鸽笼")
    @GetMapping("/getAllDove/{pageNum}/{pageSize}")
    public Result list(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                       @RequestParam("baseId") Long baseId, @RequestParam("dovecoteNumber") String dovecoteNumber) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<CageRealVo> list = cageRealService.getAllCage(baseId, dovecoteNumber);
        Page<CageRealVo> page1 = PageUtil.list2Page(list,pageNum,pageSize);
        Page<CageRealVo> page2 = cageRealService.addAbnormal(page1);
        long end = System.currentTimeMillis();
        System.out.println("总耗时"+(end - start));
        return Result.success("查询成功").data(page2);
    }

    @ApiOperation("获取未产卵鸽笼")
    @GetMapping("/getLayEggsTime/{pageNum}/{pageSize}")
    public Result getLayEggsTime(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                                 @RequestParam("baseId") Long baseId, @RequestParam("dovecoteNumber") String dovecoteNumber){
        List<CageRealVo> list = cageRealService.getLayEggsTime(baseId, dovecoteNumber);
        Page<CageRealVo> page1 = PageUtil.list2Page(list,pageNum,pageSize);
        Page<CageRealVo> page2 = cageRealService.addAbnormal(page1);
        return Result.success("查询成功").data(page2);
    }

    @ApiOperation("获取孵化中鸽笼")
    @GetMapping("/getHatchTime/{pageNum}/{pageSize}")
    public Result getHatchTime(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                                 @RequestParam("baseId") Long baseId, @RequestParam("dovecoteNumber") String dovecoteNumber){
        List<CageRealVo> list = cageRealService.getHatchTime(baseId, dovecoteNumber);
        Page<CageRealVo> page1 = PageUtil.list2Page(list,pageNum,pageSize);
        Page<CageRealVo> page2 = cageRealService.addAbnormal(page1);
        return Result.success("查询成功").data(page2);
    }

    @ApiOperation("获取哺育中鸽笼")
    @GetMapping("/getFeedTime/{pageNum}/{pageSize}")
    public Result getFeedTime(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                               @RequestParam("baseId") Long baseId, @RequestParam("dovecoteNumber") String dovecoteNumber) {
        List<CageRealVo> list = cageRealService.getFeedTime(baseId, dovecoteNumber);
        Page<CageRealVo> page1 = PageUtil.list2Page(list,pageNum,pageSize);
        Page<CageRealVo> page2 = cageRealService.addAbnormal(page1);
        return Result.success("查询成功").data(page2);
    }

    @ApiOperation("获得各状态的鸽笼信息")
    @GetMapping("/getCageOfDiffState")
    public Result getCageOfDiffState(@RequestParam("baseId")Long baseId,@RequestParam("dovecoteNumber")String dovecoteNumber,
                                     @RequestParam("state")String state,@RequestParam("pageNum")int pageNum,
                                     @RequestParam("pageSize")int pageSize) throws InterruptedException {
        Page<CageRealVo> page = cageRealService.getCageOfDiffState(baseId, dovecoteNumber, state, pageNum, pageSize);
        return Result.success("获取成功").data(page) ;
    }

    @ApiOperation("根据cageId查鸽笼状态")
    @GetMapping("/getStateByCageId")
    public Result getStateByCageId(@RequestParam("cageId")Long cageId){
        CageRealVo cageRealVo = cageRealService.getStateByCageId(cageId);
        return Result.success("获取成功").data(cageRealVo);
    }
}

