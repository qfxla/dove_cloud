package com.dove.processing.controller;



import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dove.processing.entity.Dto.OutProcessingDto;
import com.dove.processing.entity.OutProcessing;
import com.dove.processing.entity.OutProcessingBill;
import com.dove.processing.entity.Vo.*;
import com.dove.processing.listener.OutProcessingListener;
import com.dove.processing.service.OutProcessingBillService;
import com.dove.processing.service.OutProcessingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.entity.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.utils.ConvertUtil;
import com.dove.processing.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
@CrossOrigin
@RestController
@RequestMapping("/processing/out-processing")
public class OutProcessingController {

    @Resource
    private OutProcessingService outProcessingService;

    @Resource
    private OutProcessingBillService outProcessingBillService;

    @Resource
    private ConvertUtil convertUtil;

    @Resource
    private ExcelUtil excelUtil;

    @Resource
    private OutProcessingListener outProcessingListener;

    @ApiOperation(value = "新增")
    @PostMapping("/")
    public Result save(@RequestBody OutProcessingDto outProcessingDto){
        boolean isSuccess =outProcessingService.saveBothInfo(outProcessingDto);
        return  isSuccess ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "表格导入出库单数据")
    @PostMapping("/excel")
    public Result saveByExcel(@ApiParam("要导入的excel文件") @RequestPart MultipartFile file) throws IOException {
        excelUtil.read(file, OutProcessInfoVo.class,outProcessingListener);
        return Result.success("导入成功");
    }

    @ApiOperation(value = "根据表out_id删除")
    @DeleteMapping("/deletion/{id}")
    public Result delete(@PathVariable("id") long id){
        Map<String,Object> map = new HashMap<>();
        map.put("out_id",id);
        boolean deleteById = outProcessingService.removeByMap(map) && outProcessingBillService.removeByMap(map);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据主键id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = outProcessingService.removeByIds(ids) && outProcessingBillService.deleteByIds(ids); ;
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }


    @ApiOperation(value = "出库信息列表（分页）")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
       Page<OutProcessingBothBindVo> page = outProcessingService.getProcessingInfoByPage(pageNum,pageSize);
       return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "加工厂出库信息详情(传out_id)")
    @GetMapping("/info/{id}")
    public Result get(@PathVariable("id") long id){
        List<OutProcessingBothBindVo> outProcessing = outProcessingService.getInfoById(id);
        return outProcessing.size() > 0 ? Result.success("查询详情成功").data(outProcessing) : Result.error("查询失败");
    }

//    @ApiOperation(value = "根据商家id查询该加工产品由那个加工厂所加工及其详细信息（分页）")
//    @GetMapping("/query/{consignee}/{no}/{size}")
//    public Result getFactoryInfoByConsignee(@ApiParam("商家id")@PathVariable("consignee") Long consignee,
//                                            @ApiParam("第几页")@PathVariable("no") int no,
//                                            @ApiParam("每页显示条数")@PathVariable("size") int size) {
//        Page<DoveProcessingVo> getFactoryByConsignee = outProcessingService.getFactoryByBossId(consignee,no,size);
//        return getFactoryByConsignee.getTotal() > 0 ? Result.success("查询成功").data(getFactoryByConsignee) : Result.error().data("查询失败");
//    }

    @ApiOperation(value = "模糊查询获取出库信息表数据（分页）",notes = "分页 根据加工产品类型名 商家名称")
    @GetMapping("/like/{value}/{no}/{size}")
    public Result getFactorysByLikeSearch(@ApiParam("加工产品类型名或商家名称") @PathVariable("value") String value,
                                          @ApiParam("第几页") @PathVariable("no") int no,
                                          @ApiParam("每页规格") @PathVariable("size") int size) {
        Page<OutProcessingBothBindVo> getProcessingByLike = outProcessingService.getProcessingByLikeSearch(value,no,size);
        return getProcessingByLike.getTotal() > 0 ? Result.success("模糊查询成功").data(getProcessingByLike) : Result.error("模糊查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody OutProcessingDto outProcessingDto){
        OutProcessing outProcessing = convertUtil.convert(outProcessingDto, OutProcessing.class);
        outProcessing.setOutId(id);
        boolean updateInfo = outProcessingService.updateById(outProcessing) && outProcessingService.updateBillInfo(id,outProcessingDto);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "根据一个时间段查询东西（分页）")
    @GetMapping("/get/{no}/{size}")
    public Result getInfoByTiemStamp(@ApiParam("第几页") @PathVariable("no") int no,
                                     @ApiParam("每页规格") @PathVariable("size") int size,
                                     @ApiParam("开始时间") @RequestParam("firstTime") String firstTime,
                                     @ApiParam("结束时间") @RequestParam("lastTime") String lastTime) {
        Page<OutProcessingBothBindVo> page =outProcessingService.getDataByDateTime(no,size,firstTime,lastTime);
        return page.getTotal() > 0 ? Result.success("查询成功").data(page) : Result.error("数据为空");
    }
}
