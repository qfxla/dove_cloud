package com.dove.processing.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.Dto.ProcessingStorageDto;
import com.dove.processing.entity.ProcessingStorage;
import com.dove.processing.service.ProcessingStorageService;
import com.dove.processing.utils.ConvertUtil;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
    * 加工厂库存表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-23
*/

@Slf4j
@Api(tags = "加工厂库存表")
@CrossOrigin
@RestController
@RequestMapping("/processing/processingStorage")
public class ProcessingStorageController {

    @Resource
    private ProcessingStorageService processingStorageService;

    @Resource
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ProcessingStorageDto processingStorageDto){
        ProcessingStorage processingStorage = convertUtil.convert(processingStorageDto, ProcessingStorage.class);
        boolean addInfo = processingStorageService.save(processingStorage);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteByid = processingStorageService.removeById(id);
        return deleteByid ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据主键id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = processingStorageService.removeByIds(ids);
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody ProcessingStorageDto processingStorageDto){
        ProcessingStorage processingStorage = convertUtil.convert(processingStorageDto, ProcessingStorage.class);
        List<ProcessingStorage> processingStorageList = processingStorageService.list(new QueryWrapper<>(processingStorage));
        return processingStorageList.size() > 0 ? Result.success("查询成功").data(processingStorageList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        IPage<ProcessingStorage> page = processingStorageService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        ProcessingStorage processingStorage = processingStorageService.getById(id);
        return processingStorage != null ? Result.success("查询详情成功").data(processingStorage) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody ProcessingStorageDto processingStorageDto){
        ProcessingStorage processingStorage = convertUtil.convert(processingStorageDto, ProcessingStorage.class);
        processingStorage.setId(id);
        boolean updateInfo = processingStorageService.updateById(processingStorage);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }


}
