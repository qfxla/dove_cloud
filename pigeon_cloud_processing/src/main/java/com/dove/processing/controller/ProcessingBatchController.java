package com.dove.processing.controller;



import com.dove.processing.entity.Dto.ProcessingBatchDto;
import com.dove.processing.entity.ProcessingBatch;
import com.dove.processing.service.ProcessingBatchService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.entity.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.utils.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* <p>
    * 加工批次表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "加工批次表")
@CrossOrigin
@RestController
@RequestMapping("/processing/processing-batch")
public class ProcessingBatchController {

    @Resource
    private ProcessingBatchService processingBatchService;

    @Resource
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/")
    public Result save(@RequestBody ProcessingBatchDto processingBatchDto){
        ProcessingBatch processingBatch = convertUtil.convert(processingBatchDto, ProcessingBatch.class);
        boolean addInfo = processingBatchService.save(processingBatch);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @DeleteMapping("/deletion/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteById = processingBatchService.removeById(id);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据主键id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = processingBatchService.removeByIds(ids);
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/condition")
    public Result list(@RequestBody ProcessingBatchDto processingBatchDto){
        ProcessingBatch processingBatch = convertUtil.convert(processingBatchDto, ProcessingBatch.class);
        List<ProcessingBatch> processingBatchList = processingBatchService.list(new QueryWrapper<>(processingBatch));
        return processingBatchList.size() > 0 ? Result.success("查询成功").data(processingBatchList) : Result.error("查询失败");
    }

    @ApiOperation(value = "查询所有加工批次信息列表（分页）")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        IPage<ProcessingBatch> page = processingBatchService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/info/{id}")
    public Result get(@PathVariable("id") long id){
        ProcessingBatch processingBatch = processingBatchService.getById(id);
        return processingBatch != null ? Result.success("查询详情成功").data(processingBatch) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody ProcessingBatchDto processingBatchDto){
        ProcessingBatch processingBatch = convertUtil.convert(processingBatchDto, ProcessingBatch.class);
        processingBatch.setBatchId(id);
        boolean updateInfo = processingBatchService.updateById(processingBatch);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

}

