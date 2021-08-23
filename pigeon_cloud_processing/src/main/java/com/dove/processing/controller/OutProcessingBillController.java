package com.dove.processing.controller;



import com.dove.processing.entity.Dto.OutProcessingBillDto;
import com.dove.processing.entity.OutProcessingBill;
import com.dove.processing.entity.Vo.BillBindInfoVo;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.OutProcessingBillVo;
import com.dove.processing.listener.ProcessBillDataListener;
import com.dove.processing.service.OutProcessingBillService;
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
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
* <p>
    * 加工厂出库单表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "加工厂出库单表")
@CrossOrigin
@RestController
@RequestMapping("/processing/out-processingbill")
public class OutProcessingBillController {

    @Resource
    private OutProcessingBillService outProcessingBillService;

    @Resource
    private ConvertUtil convertUtil;

    @Resource
    private ExcelUtil excelUtil;

    @Resource
    private ProcessBillDataListener processBillDataListener;

    @ApiOperation(value = "新增")
    @PostMapping("/")
    public Result save(@RequestBody OutProcessingBillDto outProcessingBillDto){
        OutProcessingBill outProcessingBill = convertUtil.convert(outProcessingBillDto, OutProcessingBill.class);
        boolean addInfo = outProcessingBillService.save(outProcessingBill);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "表格导入出库单数据")
    @PostMapping("/excel")
    public Result saveByExcel(@ApiParam("要导入的excel文件") @RequestPart MultipartFile file) throws IOException {
        excelUtil.read(file,OutProcessingBillVo.class,processBillDataListener);
        return Result.success("导入成功");
    }

    @ApiOperation(value = "表格excel导出出库单数据",notes = "已把两个关联的表连在一起导出，故另一个不用excel导出")
    @GetMapping("/download/{fileName}")
    public void downloadExcel(HttpServletResponse response,
                                @ApiParam("文件名字") @PathVariable("fileName") String fileName) throws IOException {
        excelUtil.write(response,fileName, BillBindInfoVo.class,"出库单表",outProcessingBillService.getBillInfosByNoPage());
    }

    @ApiOperation(value = "根据表id删除")
    @DeleteMapping("/deletion/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteById = outProcessingBillService.removeById(id);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据主键id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = outProcessingBillService.removeByIds(ids);
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/info")
    public Result list(@RequestBody OutProcessingBillDto outProcessingBillDto){
        OutProcessingBill outProcessingBill = convertUtil.convert(outProcessingBillDto, OutProcessingBill.class);
        List<OutProcessingBill> outProcessingBillList = outProcessingBillService.list(new QueryWrapper<>(outProcessingBill));
        return outProcessingBillList.size() > 0 ? Result.success("查询成功").data(outProcessingBillList) : Result.error("查询失败");
    }

    @ApiOperation(value = "查询全部出库信息列表（分页）")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        Page<OutProcessingBillVo> outProcessingBillVoPage = outProcessingBillService.getBillInfoByPage(pageNum,pageSize);
        return outProcessingBillVoPage.getTotal() > 0 ? Result.success("分页成功").data(outProcessingBillVoPage) : Result.error("分页失败");
    }

    @ApiOperation(value = "加工厂出库信息详情")
    @GetMapping("/information/{id}")
    public Result get(@PathVariable("id") long id){
        OutProcessingBill outProcessingBill = outProcessingBillService.getById(id);
        return outProcessingBill != null ? Result.success("查询详情成功").data(convertUtil.convert(outProcessingBill, OutProcessingBillVo.class)) : Result.error("查询失败");
    }

    @ApiOperation("根据商家id查询出商家的信息（分页）")
    @GetMapping("/query/{consignee}/{no}/{size}")
    public Result getFactoryInfoByConsignee(@ApiParam("商家id")@PathVariable("consignee") Long consignee,
                                            @ApiParam("第几页")@PathVariable("no") int no,
                                            @ApiParam("每页显示条数")@PathVariable("size") int size) {
        Page<BusinessProcessingVo> getFactoryByConsignee = outProcessingBillService.getBusInfoByBossId(consignee,no,size);
        return getFactoryByConsignee.getTotal() > 0 ? Result.success("查询成功").data(getFactoryByConsignee) : Result.error("查询失败");
    }

    @ApiOperation(value = "模糊查询获取出库单信息（分页）",notes = "分页 根据商家名称 经手人")
    @GetMapping("/like/{value}/{no}/{size}")
    public Result getFactorysByLikeSearch(@ApiParam("加工厂名称或者联系人") @PathVariable("value") String value,
                                          @ApiParam("第几页") @PathVariable("no") int no,
                                          @ApiParam("每页规格") @PathVariable("size") int size) {
        Page<OutProcessingBillVo> processingBillVoPage = outProcessingBillService.getBillByLikeSearch(value,no,size);
        return processingBillVoPage.getTotal() > 0 ? Result.success("模糊查询成功").data(processingBillVoPage) : Result.error("模糊查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody OutProcessingBillDto outProcessingBillDto){
        OutProcessingBill outProcessingBill = convertUtil.convert(outProcessingBillDto, OutProcessingBill.class);
        outProcessingBill.setId(id);
        boolean updateInfo = outProcessingBillService.updateById(outProcessingBill);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

}
