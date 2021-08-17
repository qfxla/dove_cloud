package com.pigeon.processing.controller;



import com.pigeon.processing.entity.Dto.OutProcessingDto;
import com.pigeon.processing.service.OutProcessingService;
import com.pigeon.processing.entity.OutProcessing;
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
    * 加工厂出库表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "加工厂出库表")
@RestController
@RequestMapping("//outProcessing")
public class OutProcessingController {

    @Autowired
    private OutProcessingService outProcessingService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody OutProcessingDto outProcessingDto){
        OutProcessing outProcessing = convertUtil.convert(outProcessingDto, OutProcessing.class);
        boolean addInfo = outProcessingService.save(outProcessing);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteByid = outProcessingService.removeById(id);
        return deleteByid ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody OutProcessingDto outProcessingDto){
        OutProcessing outProcessing = convertUtil.convert(outProcessingDto, OutProcessing.class);
        List<OutProcessing> outProcessingList = outProcessingService.list(new QueryWrapper<>(outProcessing));
        return outProcessingList.size() > 0 ? Result.success("查询成功").data(outProcessingList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<OutProcessing> page = outProcessingService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        OutProcessing outProcessing = outProcessingService.getById(id);
        return outProcessing != null ? Result.success("查询详情成功").data(outProcessing) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody OutProcessingDto outProcessingDto){
        OutProcessing outProcessing = convertUtil.convert(outProcessingDto, OutProcessing.class);
        outProcessing.setOutId(id);
        boolean updateInfo = outProcessingService.updateById(outProcessing);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }


}
