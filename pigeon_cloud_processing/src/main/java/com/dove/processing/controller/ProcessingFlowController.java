package com.dove.processing.controller;



import com.dove.processing.entity.Dto.ProcessingFlowDto;
import com.dove.processing.service.ProcessingFlowService;
import com.dove.processing.entity.ProcessingFlow;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.entity.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.utils.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* <p>
    * 加工流程表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "加工流程表")
@RestController
@RequestMapping("/processing/processing-flow")
public class ProcessingFlowController {

    @Resource
    private ProcessingFlowService processingFlowService;

    @Resource
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/")
    public Result save(@RequestBody ProcessingFlowDto processingFlowDto){
        ProcessingFlow processingFlow = convertUtil.convert(processingFlowDto, ProcessingFlow.class);
        boolean addInfo = processingFlowService.save(processingFlow);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @DeleteMapping("/deletion/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteById = processingFlowService.removeById(id);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @GetMapping("/condition")
    public Result list(@RequestBody ProcessingFlowDto processingFlowDto){
        ProcessingFlow processingFlow = convertUtil.convert(processingFlowDto, ProcessingFlow.class);
        List<ProcessingFlow> processingFlowList = processingFlowService.list(new QueryWrapper<>(processingFlow));
        return processingFlowList.size() > 0 ? Result.success("查询成功").data(processingFlowList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ProcessingFlow> page = processingFlowService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/infomation/{id}")
    public Result get(@PathVariable("id") long id){
        ProcessingFlow processingFlow = processingFlowService.getById(id);
        return processingFlow != null ? Result.success("查询详情成功").data(processingFlow) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody ProcessingFlowDto processingFlowDto){
        ProcessingFlow processingFlow = convertUtil.convert(processingFlowDto, ProcessingFlow.class);
        processingFlow.setProcessId(id);
        boolean updateInfo = processingFlowService.updateById(processingFlow);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

}
