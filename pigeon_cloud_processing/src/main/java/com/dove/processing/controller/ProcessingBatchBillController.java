package com.dove.processing.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.entity.Result;
import com.dove.processing.entity.Dto.ProcessingBatchBillDto;
import com.dove.processing.entity.ProcessingBatchBill;
import com.dove.processing.entity.Vo.OutProcessingBothBindVo;
import com.dove.processing.entity.Vo.ProcessingBatchBillVo;
import com.dove.processing.service.ProcessingBatchBillService;
import com.dove.processing.utils.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
    * 加工批次信息表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-30
*/

@Slf4j
@Api(tags = "加工批次信息表")
@CrossOrigin
@RestController
@RequestMapping("/processing/processingBatchBill")
public class ProcessingBatchBillController {

    @Autowired
    private ProcessingBatchBillService processingBatchBillService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ProcessingBatchBillDto processingBatchBillDto){
        ProcessingBatchBill processingBatchBill = convertUtil.convert(processingBatchBillDto, ProcessingBatchBill.class);
        boolean addInfo = processingBatchBillService.save(processingBatchBill);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteByid = processingBatchBillService.removeById(id);
        return deleteByid ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据主键id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = processingBatchBillService.removeByIds(ids);
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        Page<ProcessingBatchBillVo> page = processingBatchBillService.getBatchBillInfoByPage(pageNum,pageSize);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        ProcessingBatchBill processingBatchBill = processingBatchBillService.getById(id);
        return processingBatchBill != null ? Result.success("查询详情成功").data(processingBatchBill) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody ProcessingBatchBillDto processingBatchBillDto){
        ProcessingBatchBill processingBatchBill = convertUtil.convert(processingBatchBillDto, ProcessingBatchBill.class);
        processingBatchBill.setId(id);
        boolean updateInfo = processingBatchBillService.updateById(processingBatchBill);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

}
