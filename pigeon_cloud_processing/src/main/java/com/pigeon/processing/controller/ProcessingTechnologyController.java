package com.pigeon.processing.controller;



import com.pigeon.processing.entity.Dto.ProcessingTechnologyDto;
import com.pigeon.processing.service.ProcessingTechnologyService;
import com.pigeon.processing.entity.ProcessingTechnology;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pigeon.entity.Result;
import com.pigeon.entity.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigeon.processing.utils.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 加工工艺表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "加工工艺表")
@RestController
@RequestMapping("//processingTechnology")
public class ProcessingTechnologyController {

    @Autowired
    private ProcessingTechnologyService processingTechnologyService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ProcessingTechnologyDto processingTechnologyDto){
        ProcessingTechnology processingTechnology = convertUtil.convert(processingTechnologyDto, ProcessingTechnology.class);
        boolean addInfo = processingTechnologyService.save(processingTechnology);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteByid = processingTechnologyService.removeById(id);
        return deleteByid ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody ProcessingTechnologyDto processingTechnologyDto){
        ProcessingTechnology processingTechnology = convertUtil.convert(processingTechnologyDto, ProcessingTechnology.class);
        List<ProcessingTechnology> processingTechnologyList = processingTechnologyService.list(new QueryWrapper<>(processingTechnology));
        return processingTechnologyList.size() > 0 ? Result.success("查询成功").data(processingTechnologyList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ProcessingTechnology> page = processingTechnologyService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        ProcessingTechnology processingTechnology = processingTechnologyService.getById(id);
        return processingTechnology != null ? Result.success("查询详情成功").data(processingTechnology) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody ProcessingTechnologyDto processingTechnologyDto){
        ProcessingTechnology processingTechnology = convertUtil.convert(processingTechnologyDto, ProcessingTechnology.class);
        processingTechnology.setTechnologyId(id);
        boolean updateInfo = processingTechnologyService.updateById(processingTechnology);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }


}
