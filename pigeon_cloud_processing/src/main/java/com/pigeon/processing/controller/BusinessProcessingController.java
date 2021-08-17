package com.pigeon.processing.controller;



import com.pigeon.processing.entity.Dto.BusinessProcessingDto;
import com.pigeon.processing.service.BusinessProcessingService;
import com.pigeon.processing.entity.BusinessProcessing;
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
    * 商家表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "商家表")
@RestController
@RequestMapping("//businessProcessing")
public class BusinessProcessingController {

    @Autowired
    private BusinessProcessingService businessProcessingService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody BusinessProcessingDto businessProcessingDto){
        BusinessProcessing businessProcessing = convertUtil.convert(businessProcessingDto, BusinessProcessing.class);
        boolean addInfo = businessProcessingService.save(businessProcessing);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteByid = businessProcessingService.removeById(id);
        return deleteByid ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody BusinessProcessingDto businessProcessingDto){
        BusinessProcessing businessProcessing = convertUtil.convert(businessProcessingDto, BusinessProcessing.class);
        List<BusinessProcessing> businessProcessingList = businessProcessingService.list(new QueryWrapper<>(businessProcessing));
        return businessProcessingList.size() > 0 ? Result.success("查询成功").data(businessProcessingList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<BusinessProcessing> page = businessProcessingService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        BusinessProcessing businessProcessing = businessProcessingService.getById(id);
        return businessProcessing != null ? Result.success("查询详情成功").data(businessProcessing) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody BusinessProcessingDto businessProcessingDto){
        BusinessProcessing businessProcessing = convertUtil.convert(businessProcessingDto, BusinessProcessing.class);
        businessProcessing.setId(id);
        boolean updateInfo = businessProcessingService.updateById(businessProcessing);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }


}
