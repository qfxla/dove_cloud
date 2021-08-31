package com.dove.breed.controller;

import com.dove.entity.Result;

import com.dove.breed.service.CageDailyService;
import com.dove.breed.entity.CageDaily;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 鸽笼日结表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/

@Slf4j
@Api(tags = "鸽笼日结表")
@RestController
@RequestMapping("/breed/cageDaily")
public class CageDailyController {

    @Autowired
    public CageDailyService cageDailyService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody CageDaily cageDaily){
        boolean save = cageDailyService.save(cageDaily);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = cageDailyService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody CageDaily cageDaily){
        List<CageDaily> cageDailyList = cageDailyService.list(new QueryWrapper<>(cageDaily));
        return cageDailyList.size() > 0?Result.success("查询成功").data(cageDailyList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<CageDaily> page = cageDailyService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0?Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        CageDaily cageDaily = cageDailyService.getById(id);
        return cageDaily != null? Result.success("查询成功").data(cageDaily) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody CageDaily cageDaily){
        cageDaily.setId(id);
        boolean b = cageDailyService.updateById(cageDaily);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
