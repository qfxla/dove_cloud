package com.dove.processing.controller;



import com.dove.processing.entity.Dto.DoveProcessingDto;
import com.dove.processing.service.DoveProcessingService;
import com.dove.processing.entity.DoveProcessing;
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
    * 加工厂表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "加工厂表")
@RestController
@RequestMapping("/processing/doveProcessing")
public class DoveProcessingController {

    @Resource
    private DoveProcessingService doveProcessingService;

    @Resource
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DoveProcessingDto doveProcessingDto){
        DoveProcessing doveProcessing = convertUtil.convert(doveProcessingDto, DoveProcessing.class);
        boolean addInfo = doveProcessingService.save(doveProcessing);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteById = doveProcessingService.removeById(id);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody DoveProcessingDto doveProcessingDto){
        DoveProcessing doveProcessing = convertUtil.convert(doveProcessingDto, DoveProcessing.class);
        List<DoveProcessing> doveProcessingList = doveProcessingService.list(new QueryWrapper<>(doveProcessing));
        return doveProcessingList.size() > 0 ? Result.success("查询成功").data(doveProcessingList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<DoveProcessing> page = doveProcessingService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        DoveProcessing doveProcessing = doveProcessingService.getById(id);
        return doveProcessing != null ? Result.success("查询详情成功").data(doveProcessing) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody DoveProcessingDto doveProcessingDto){
        DoveProcessing doveProcessing = convertUtil.convert(doveProcessingDto, DoveProcessing.class);
        doveProcessing.setProcessingId(id);
        boolean updateInfo = doveProcessingService.updateById(doveProcessing);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }


}
