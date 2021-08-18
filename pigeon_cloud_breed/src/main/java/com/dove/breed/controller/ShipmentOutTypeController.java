package com.dove.breed.controller;
import com.dove.entity.Result;


import com.dove.breed.service.ShipmentOutTypeService;
import com.dove.breed.entity.ShipmentOutType;
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
    * 基地出库类型表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/

@Slf4j
@Api(tags = "基地出库类型表")
@RestController
@RequestMapping("/breed/shipmentOutType")
public class ShipmentOutTypeController {

    @Autowired
    public ShipmentOutTypeService shipmentOutTypeService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ShipmentOutType shipmentOutType){
        boolean save = shipmentOutTypeService.save(shipmentOutType);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = shipmentOutTypeService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody ShipmentOutType shipmentOutType){
        List<ShipmentOutType> shipmentOutTypeList = shipmentOutTypeService.list(new QueryWrapper<>(shipmentOutType));
        return shipmentOutTypeList.size() > 0?Result.success("查询成功").data(shipmentOutTypeList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ShipmentOutType> page = shipmentOutTypeService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0?Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        ShipmentOutType shipmentOutType = shipmentOutTypeService.getById(id);
        return shipmentOutType == null? Result.success("查询成功").data(shipmentOutType) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody ShipmentOutType shipmentOutType){
        shipmentOutType.setTypeId(id);
        boolean b = shipmentOutTypeService.updateById(shipmentOutType);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
