package com.dove.processing.controller;



import com.dove.processing.entity.Dto.ProcessingTypeDto;
import com.dove.processing.entity.ProcessingType;
import com.dove.processing.entity.Vo.*;
import com.dove.processing.service.ProcessingTypeService;
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
    * 加工产品类型表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "加工产品类型表")
@CrossOrigin
@RestController
@RequestMapping("/processing/processing-type")
public class ProcessingTypeController {

    @Resource
    private ProcessingTypeService processingTypeService;

    @Resource
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/")
    public Result save(@RequestBody ProcessingTypeDto processingTypeDto){
        ProcessingType processingType = convertUtil.convert(processingTypeDto, ProcessingType.class);
        boolean addInfo = processingTypeService.save(processingType);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @DeleteMapping("/deletion/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteById = processingTypeService.removeById(id);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据主键id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = processingTypeService.removeByIds(ids);
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        Page<ProcessingTypeVo> page = processingTypeService.getTypeByPage(pageNum,pageSize);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "查询加工产品类型详情")
    @GetMapping("/information/{id}")
    public Result get(@PathVariable("id") long id){
        ProcessingType processingType = processingTypeService.getById(id);
        return processingType != null ? Result.success("查询详情成功").data(processingType) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody ProcessingTypeDto processingTypeDto){
        ProcessingType processingType = convertUtil.convert(processingTypeDto, ProcessingType.class);
        processingType.setTypeId(id);
        boolean updateInfo = processingTypeService.updateById(processingType);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "根据加工产品类型type_id查询该工艺产品下对应的加工流程(分页)")
    @GetMapping("/getInfo/{id}/{no}/{size}")
    public Result getInfo(@ApiParam("加工工艺id") @PathVariable("id") Long id,
                          @ApiParam("第几页") @PathVariable("no") int no,
                          @ApiParam("每页规格") @PathVariable("size") int size) {
        Page<ProcessingFlowVo> getFlowByPage =processingTypeService.getFlowInfoByPage(id,no,size);
        return getFlowByPage.getTotal() > 0 ? Result.success("查询成功").data(getFlowByPage) : Result.error("数据为空");
    }

}
