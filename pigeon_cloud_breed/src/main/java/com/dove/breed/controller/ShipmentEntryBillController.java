package com.dove.breed.controller;
import com.dove.entity.Result;


import com.dove.breed.service.ShipmentEntryBillService;
import com.dove.breed.entity.ShipmentEntryBill;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 基地进库单 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/

@Slf4j
@Api(tags = "基地进库单")
@RestController
@RequestMapping("/breed/shipmentEntryBill")
public class ShipmentEntryBillController {

    @Autowired
    public ShipmentEntryBillService shipmentEntryBillService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ShipmentEntryBill shipmentEntryBill){
        boolean save = shipmentEntryBillService.save(shipmentEntryBill);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = shipmentEntryBillService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody ShipmentEntryBill shipmentEntryBill){
        List<ShipmentEntryBill> shipmentEntryBillList = shipmentEntryBillService.list(new QueryWrapper<>(shipmentEntryBill));
        return shipmentEntryBillList.size() > 0?Result.success("查询成功").data(shipmentEntryBillList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ShipmentEntryBill> page = shipmentEntryBillService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0?Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        ShipmentEntryBill shipmentEntryBill = shipmentEntryBillService.getById(id);
        return shipmentEntryBill == null? Result.success("查询成功").data(shipmentEntryBill) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody ShipmentEntryBill shipmentEntryBill){
        shipmentEntryBill.setId(id);
        boolean b = shipmentEntryBillService.updateById(shipmentEntryBill);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
