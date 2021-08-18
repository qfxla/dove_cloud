package com.dove.processing.controller;



import com.dove.processing.entity.Dto.OutProcessingBillDto;
import com.dove.processing.service.OutProcessingBillService;
import com.dove.processing.entity.OutProcessingBill;
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
    * 加工厂出库单表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-17
*/

@Slf4j
@Api(tags = "加工厂出库单表")
@RestController
@RequestMapping("/processing/outProcessingBill")
public class OutProcessingBillController {

    @Resource
    private OutProcessingBillService outProcessingBillService;

    @Resource
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody OutProcessingBillDto outProcessingBillDto){
        OutProcessingBill outProcessingBill = convertUtil.convert(outProcessingBillDto, OutProcessingBill.class);
        boolean addInfo = outProcessingBillService.save(outProcessingBill);
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteById = outProcessingBillService.removeById(id);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody OutProcessingBillDto outProcessingBillDto){
        OutProcessingBill outProcessingBill = convertUtil.convert(outProcessingBillDto, OutProcessingBill.class);
        List<OutProcessingBill> outProcessingBillList = outProcessingBillService.list(new QueryWrapper<>(outProcessingBill));
        return outProcessingBillList.size() > 0 ? Result.success("查询成功").data(outProcessingBillList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<OutProcessingBill> page = outProcessingBillService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        OutProcessingBill outProcessingBill = outProcessingBillService.getById(id);
        return outProcessingBill != null ? Result.success("查询详情成功").data(outProcessingBill) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody OutProcessingBillDto outProcessingBillDto){
        OutProcessingBill outProcessingBill = convertUtil.convert(outProcessingBillDto, OutProcessingBill.class);
        outProcessingBill.setId(id);
        boolean updateInfo = outProcessingBillService.updateById(outProcessingBill);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }


}
