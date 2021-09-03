package com.dove.processing.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.Dto.ProcessingStorageDto;
import com.dove.processing.entity.ProcessingStorage;
import com.dove.processing.entity.Vo.BillBindInfoVo;
import com.dove.processing.entity.Vo.ProcessingFlowVo;
import com.dove.processing.entity.Vo.ProcessingStorageVo;
import com.dove.processing.service.ProcessingStorageService;
import com.dove.processing.utils.ConvertUtil;
import com.dove.entity.Result;
import com.dove.processing.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
    * 加工厂库存表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-23
*/

@Slf4j
@Api(tags = "加工厂库存表")
@CrossOrigin
@RestController
@RequestMapping("/processing/processingStorage")
public class ProcessingStorageController {

    @Resource
    private ProcessingStorageService processingStorageService;

    @Resource
    private ConvertUtil convertUtil;

    @Resource
    private ExcelUtil excelUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ProcessingStorageDto processingStorageDto){
        ProcessingStorage processingStorage = convertUtil.convert(processingStorageDto, ProcessingStorage.class);
        boolean addInfo = processingStorageService.save(processingStorage);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteByid = processingStorageService.removeById(id);
        return deleteByid ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据主键id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = processingStorageService.removeByIds(ids);
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        Page<ProcessingStorageVo> page = processingStorageService.getStorageByPage(pageNum,pageSize);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "查询库存信息详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        ProcessingStorage processingStorage = processingStorageService.getById(id);
        return processingStorage != null ? Result.success("查询详情成功").data(processingStorage) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody ProcessingStorageDto processingStorageDto){
        ProcessingStorage processingStorage = convertUtil.convert(processingStorageDto, ProcessingStorage.class);
        processingStorage.setId(id);
        boolean updateInfo = processingStorageService.updateById(processingStorage);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "模糊查询获取库存信息表数据（分页）",notes = "分页 根据产品名称 备注")
    @GetMapping("/like/{value}/{no}/{size}")
    public Result getStoragesByLikeSearch(@ApiParam("加工产品类型名或商家名称") @PathVariable("value") String value,
                                          @ApiParam("第几页") @PathVariable("no") int no,
                                          @ApiParam("每页规格") @PathVariable("size") int size) {
        Page<ProcessingStorageVo> getProcessingByLike =processingStorageService.getStorageByLikeSearch(value,no,size);
        return getProcessingByLike.getTotal() > 0 ? Result.success("模糊查询成功").data(getProcessingByLike) : Result.error("模糊查询失败");
    }

    @ApiOperation(value = "表格excel导出出库单数据",notes = "excel导出,使用no,size便于用户选择【主要是我懒】")
    @GetMapping("/download/{fileName}/{no}/{size}")
    public void downloadExcel(HttpServletResponse response,
                              @ApiParam("文件名字") @PathVariable("fileName") String fileName,
                              @ApiParam("第几页") @PathVariable("no") int no,
                              @ApiParam("每页规格") @PathVariable("size") int size) throws IOException {
        excelUtil.write(response,fileName, ProcessingStorageVo.class,"库存表",processingStorageService.getStorageByPage(no,size).getRecords());
    }

}
