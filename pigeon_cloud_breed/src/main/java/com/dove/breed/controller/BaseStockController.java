package com.dove.breed.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.BaseStock;
import com.dove.breed.entity.dto.BaseStockDto;
import com.dove.breed.entity.vo.BaseStockVo;
import com.dove.breed.service.BaseStockService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 基地库存表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-06
 */
@Api(tags = "基地库存")
@CrossOrigin
@RestController
@RequestMapping("/breed/base-stock")
public class BaseStockController {

    @Autowired
    private BaseStockService baseStockService;
    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation("修改库存")
    @PostMapping("modifiedStock")
    public Result modifiedStock(@RequestParam("id")Long id,
                                @RequestBody BaseStockDto baseStockDto){
        BaseStock baseStock = convertUtil.convert(baseStockDto, BaseStock.class);
        baseStock.setId(id);
        boolean b = baseStockService.updateById(baseStock);
        return b?Result.success("修改成功"):Result.error("修改失败");
    }

    @ApiOperation("根据id查")
    @GetMapping("getById")
    public Result getById(@RequestParam("id")Long id){
        BaseStock baseStock = baseStockService.getById(id);
        return baseStock != null?Result.success("获取成功").data(baseStock):Result.error("获取失败");
    }


    @ApiOperation("展示某种库存")
    @GetMapping("/getStockByBaseIdAndType")
    public Result getStockByBaseIdAndType(@RequestParam("baseId")Long baseId,
                                          @RequestParam("type")String type,
                                          @RequestParam("pageNum")int pageNum,
                                          @RequestParam("pageSize")int pageSize){
        List<BaseStock> baseStockList = baseStockService.getStockByBaseIdAndType(baseId, type);
        List<BaseStockVo> baseStockVoList = convertUtil.convert(baseStockList, BaseStockVo.class);
        Page page = PageUtil.list2Page(baseStockVoList, pageNum, pageSize);
        return Result.success("获取成功").data(page);
    }

    @ApiOperation("模糊查询")
    @GetMapping("/fuzzyquery")
    public Result fuzzyquery(@RequestParam(value = "name", required = false)String name){
        List<BaseStock> list = null;
        if (name == null){
            list = baseStockService.list(null);
        }else {
            list = baseStockService.fuzzyquery(name);
        }
        List<BaseStockVo> vo = convertUtil.convert(list, BaseStockVo.class);
        return Result.success("获取成功").data(vo);
    }
}

