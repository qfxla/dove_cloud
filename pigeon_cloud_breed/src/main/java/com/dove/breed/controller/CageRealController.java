package com.dove.breed.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dove.breed.entity.Cage;
import com.dove.breed.entity.CageReal;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.service.CageRealService;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result list(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                       @RequestParam("baseId") Long baseId, @RequestParam("dovecoteNumber") String dovecoteNumber) {
        List<CageReal> list = cageRealService.getAllCage(baseId, dovecoteNumber);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CageReal> pageFromList = PageUtil.createPageFromList(list, pageable);
        return Result.success("查询成功").data(pageFromList);
    }

    @ApiOperation("获取未产卵鸽笼")
    @GetMapping("/getLayEggsTime/{pageNum}/{pageSize}")
    public Result getLayEggsTime(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                                 @RequestParam("baseId") Long baseId, @RequestParam("dovecoteNumber") String dovecoteNumber){
        List<CageReal> list = cageRealService.getLayEggsTime(baseId, dovecoteNumber);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CageReal> pageFromList = PageUtil.createPageFromList(list, pageable);
        return Result.success("查询成功").data(pageFromList);
    }

    @ApiOperation("获取孵化中鸽笼")
    @GetMapping("/getHatchTime/{pageNum}/{pageSize}")
    public Result getHatchTime(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                                 @RequestParam("baseId") Long baseId, @RequestParam("dovecoteNumber") String dovecoteNumber){
        List<CageReal> list = cageRealService.getHatchTime(baseId, dovecoteNumber);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CageReal> pageFromList = PageUtil.createPageFromList(list, pageable);
        return Result.success("查询成功").data(pageFromList);
    }

    @ApiOperation("获取孵化中鸽笼")
    @GetMapping("/getFeedTime/{pageNum}/{pageSize}")
    public Result getFeedTime(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                               @RequestParam("baseId") Long baseId, @RequestParam("dovecoteNumber") String dovecoteNumber) {
        List<CageReal> list = cageRealService.getFeedTime(baseId, dovecoteNumber);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<CageReal> pageFromList = PageUtil.createPageFromList(list, pageable);
        return Result.success("查询成功").data(pageFromList);
    }
}

