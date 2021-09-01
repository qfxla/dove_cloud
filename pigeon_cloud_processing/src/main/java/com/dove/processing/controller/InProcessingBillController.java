package com.dove.processing.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.Dto.InProcessingBillDto;
import com.dove.processing.entity.InProcessingBill;
import com.dove.processing.entity.Vo.BillBindInfoVo;
import com.dove.processing.entity.Vo.BusinessProcessingVo;
import com.dove.processing.entity.Vo.InProcessingBothVo;
import com.dove.processing.service.InProcessingBillService;
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
    * 加工厂入库单表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-23
*/

@Slf4j
@Api(tags = "加工厂入库单表")
@CrossOrigin
@RestController
@RequestMapping("/processing/inProcessingBill")
public class InProcessingBillController {

    @Resource
    private InProcessingBillService inProcessingBillService;

    @Resource
    private ConvertUtil convertUtil;

    @Resource
    private ExcelUtil excelUtil;

//    @ApiOperation(value = "新增")
//    @PostMapping("/save")
//    public Result save(@RequestBody InProcessingBillDto inProcessingBillDto){
//        InProcessingBill inProcessingBill = convertUtil.convert(inProcessingBillDto, InProcessingBill.class);
//        boolean addInfo = inProcessingBillService.save(inProcessingBill);
//        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
//    }

    @ApiOperation(value = "表格excel导出出库单数据",notes = "已把两个关联的表连在一起导出，故另一个不用excel导出")
    @GetMapping("/download/{fileName}")
    public void downloadExcel(HttpServletResponse response,
                              @ApiParam("文件名字") @PathVariable("fileName") String fileName) throws IOException {
        excelUtil.write(response,fileName, InProcessingBothVo.class,"入库单表",inProcessingBillService.getInPrcessingInfosByNoPage());
    }

//    @ApiOperation(value = "根据表id删除")
//    @DeleteMapping("/delete/{id}")
//    public Result delete(@PathVariable("id") long id){
//        boolean deleteByid = inProcessingBillService.removeById(id);
//        return deleteByid ? Result.success("删除成功") : Result.error("删除失败");
//    }

//    @ApiOperation(value = "批量删除（根据主键id）")
//    @DeleteMapping("/deletion/batch")
//    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
//        boolean deleteBatchByIds = inProcessingBillService.removeByIds(ids);
//        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
//    }


//    @ApiOperation(value = "列表（分页）")
//    @GetMapping("/list/{pageNum}/{pageSize}")
//    public Object list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
//        IPage<InProcessingBill> page = inProcessingBillService.page(
//        new Page<>(pageNum, pageSize), null);
//        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
//    }

//    @ApiOperation(value = "根据in_id查询加工厂入库详情")
//    @GetMapping("/get/{id}")
//    public Result get(@PathVariable("id") long id){
//        InProcessingBill inProcessingBill = inProcessingBillService.getById(id);
//        return inProcessingBill != null ? Result.success("查询详情成功").data(inProcessingBill) : Result.error("查询失败");
//    }

//    @ApiOperation(value = "根据in_id修改")
//    @PutMapping("/update/{id}")
//    public Result update(@PathVariable("id") long id, @RequestBody InProcessingBillDto inProcessingBillDto){
//        InProcessingBill inProcessingBill = convertUtil.convert(inProcessingBillDto, InProcessingBill.class);
//        inProcessingBill.setInId(id);
//        boolean updateInfo = inProcessingBillService.updateById(inProcessingBill);
//        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
//    }

    @ApiOperation(value = "根据商家id查询出商家的信息(分页)")
    @GetMapping("/query/{source}/{no}/{size}")
    public Result getBossInfoBySource(@ApiParam("商家id")@PathVariable("source") Long source,
                                      @ApiParam("第几页")@PathVariable("no") int no,
                                      @ApiParam("每页显示条数")@PathVariable("size") int size) {
        Page<BusinessProcessingVo> getFactoryBySource = inProcessingBillService.getBusInfoBySource(source,no,size);
        return getFactoryBySource.getTotal() > 0 ? Result.success("查询成功").data(getFactoryBySource) : Result.error("查询失败");
    }

}
