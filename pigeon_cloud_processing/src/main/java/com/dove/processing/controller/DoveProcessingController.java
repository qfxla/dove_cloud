package com.dove.processing.controller;



import com.dove.processing.entity.DoveProcessing;
import com.dove.processing.entity.Dto.DoveProcessingDto;
import com.dove.processing.entity.Vo.DoveProcessingVo;
import com.dove.processing.service.DoveProcessingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.entity.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.utils.ConvertUtil;
import com.dove.util.SecurityContextUtil;
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
    * 加工厂表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "加工厂表")
@RestController
@CrossOrigin
@RequestMapping("/processing/dove-processing")
public class DoveProcessingController {

    @Resource
    private DoveProcessingService doveProcessingService;

    @Resource
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/")
    public Result save(@RequestBody DoveProcessingDto doveProcessingDto){
        DoveProcessing doveProcessing = convertUtil.convert(doveProcessingDto, DoveProcessing.class);
        doveProcessing.setEnterpriseId(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean addInfo = doveProcessingService.save(doveProcessing);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @DeleteMapping("/deletion/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteById = doveProcessingService.removeById(id);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据主键id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = doveProcessingService.removeByIds(ids);
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/condition")
    public Result list(@RequestBody DoveProcessingDto doveProcessingDto){
        DoveProcessing doveProcessing = convertUtil.convert(doveProcessingDto, DoveProcessing.class);
        List<DoveProcessing> doveProcessingList = doveProcessingService.list(new QueryWrapper<>(doveProcessing));
        return doveProcessingList.size() > 0 ? Result.success("查询成功").data(doveProcessingList) : Result.error("查询失败");
    }

    @ApiOperation(value = "获取所有加工厂信息（分页）")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        Page<DoveProcessingVo> page = doveProcessingService.getFactorysByPage(pageNum,pageSize);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "根据加工厂id查询加工厂信息")
    @GetMapping("/info/{id}")
    public Result get(@PathVariable("id") long id){
        DoveProcessing doveProcessing = doveProcessingService.getById(id);
        return doveProcessing != null ? Result.success("查询详情成功").data(convertUtil.convert(doveProcessing,DoveProcessingVo.class)) : Result.error("查询失败");
    }

    @ApiOperation(value = "获取当前企业下的所有加工厂信息(分页)")
    @GetMapping("/current/{enterpriseId}/{no}/{size}")
    public Result getCurrentEnterpriseFactoryPage(@ApiParam("企业id")@PathVariable("enterpriseId") Long enterpriseId,
                                                  @ApiParam("第几页")@PathVariable("no") int no,
                                                  @ApiParam("每页显示条数")@PathVariable("size") int size) {
        Page<DoveProcessingVo> processingVoPage = doveProcessingService.getFactorysByEnterprise(enterpriseId,no,size);
        return processingVoPage.getTotal() > 0 ? Result.success("查询成功").data(processingVoPage) : Result.error().data("无加工厂信息");
    }

    @ApiOperation(value = "查询当前加工厂下所生产的加工产品(分页)")
    @GetMapping("/query/{factoryId}/{no}/{size}")
    public Result getProductByCurrentFactory(@ApiParam("企业id")@PathVariable("factoryId") Long factoryId,
                                             @ApiParam("第几页")@PathVariable("no") int no,
                                             @ApiParam("每页显示条数")@PathVariable("size") int size) {
        Page<DoveProcessingVo> doveProcessingVoPage = doveProcessingService.getProductByProcessId(factoryId,no,size);
        return doveProcessingVoPage.getTotal() > 0 ? Result.success("查询成功").data(doveProcessingVoPage) : Result.error().data("无加工产品信息");
    }

    @ApiOperation(value = "模糊查询获取加工厂信息（分页）",notes = "分页 根据加工厂名称 地址")
    @GetMapping("/like/{value}/{no}/{size}")
    public Result getFactorysByLikeSearch(@ApiParam("加工厂名称或者联系人") @PathVariable("value") String value,
                                         @ApiParam("第几页") @PathVariable("no") int no,
                                         @ApiParam("每页规格") @PathVariable("size") int size) {
        Page<DoveProcessingVo> doveProcessingVoPage = doveProcessingService.getFatorcysByLike(value,no,size);
        return doveProcessingVoPage.getTotal() > 0 ? Result.success("模糊查询成功").data(doveProcessingVoPage) : Result.error().data("无匹配加工厂信息");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody DoveProcessingDto doveProcessingDto){
        DoveProcessing doveProcessing = convertUtil.convert(doveProcessingDto, DoveProcessing.class);
        doveProcessing.setProcessingId(id);
        boolean updateInfo = doveProcessingService.updateById(doveProcessing);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

}
