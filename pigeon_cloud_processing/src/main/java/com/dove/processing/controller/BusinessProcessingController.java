package com.dove.processing.controller;

import com.dove.processing.entity.BusinessProcessing;
import com.dove.processing.entity.Dto.BusinessProcessingDto;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.ProcessingTypeVo;
import com.dove.processing.service.BusinessProcessingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.entity.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.utils.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
@CrossOrigin
@RestController
@RequestMapping("/processing/business-processing")
public class BusinessProcessingController {

    @Resource
    private BusinessProcessingService businessProcessingService;

    @Resource
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/")
    public Result save(@RequestBody BusinessProcessingDto businessProcessingDto){
        BusinessProcessing businessProcessing = convertUtil.convert(businessProcessingDto, BusinessProcessing.class);
        boolean addInfo = businessProcessingService.save(businessProcessing);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @DeleteMapping("/deletion/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteById = businessProcessingService.removeById(id);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据主键id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = businessProcessingService.removeByIds(ids);
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        Page<BusinessProcessingVo> page = businessProcessingService.getBusinesssByPage(pageNum,pageSize);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "根据商家id查询商家信息详情")
    @GetMapping("/{id}")
    public Result get(@PathVariable("id") long id){
        BusinessProcessing businessProcessing = businessProcessingService.getById(id);
        return businessProcessing != null ? Result.success("查询详情成功").data(convertUtil.convert(businessProcessing,BusinessProcessingVo.class)) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据商家名称或者联系人进行模糊查找-(分页）")
    @GetMapping("/find/{value}/{no}/{size}")
    public Result getInfoByLike(@ApiParam("商家名称或者联系人") @PathVariable("value") String value,
                                @ApiParam("第几页") @PathVariable("no") int no,
                                @ApiParam("每页规格") @PathVariable("size") int size) {
        Page<BusinessProcessingVo> businessProcessingVoPage = businessProcessingService.getBusinessByBossName(value,no,size);
        return businessProcessingVoPage.getTotal() > 0 ? Result.success("模糊查询成功").data(businessProcessingVoPage) : Result.error("模糊查询失败");

    }

    @ApiOperation(value = "根据商家id查询该商家购买的加工产品（分页）")
    @GetMapping("/buy/{id}/{no}/{size}")
    public Result getProductsById(@ApiParam("商家id") @PathVariable("id") Long id,
                                  @ApiParam("第几页") @PathVariable("no") int no,
                                  @ApiParam("一页规格") @PathVariable("size") int size) {
        Page<ProcessingTypeVo> processingTypeVoPage = businessProcessingService.getProductInfoById(id,no,size);
        return processingTypeVoPage.getTotal() > 0 ? Result.success("查询成功").data(processingTypeVoPage) : Result.error("无查询数据");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody BusinessProcessingDto businessProcessingDto){
        BusinessProcessing businessProcessing = convertUtil.convert(businessProcessingDto, BusinessProcessing.class);
        businessProcessing.setId(id);
        boolean updateInfo = businessProcessingService.updateById(businessProcessing);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

}
