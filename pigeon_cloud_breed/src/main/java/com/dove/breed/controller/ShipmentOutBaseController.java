package com.dove.breed.controller;
import com.dove.entity.Result;


import com.dove.breed.service.ShipmentOutBaseService;
import com.dove.breed.entity.ShipmentOutBase;
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
    * 基地出库信息表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/

@Slf4j
@Api(tags = "基地出库信息表")
@RestController
@RequestMapping("/breed/shipmentOutBase")
public class ShipmentOutBaseController {

    @Autowired
    public ShipmentOutBaseService shipmentOutBaseService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ShipmentOutBase shipmentOutBase){
        boolean save = shipmentOutBaseService.save(shipmentOutBase);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = shipmentOutBaseService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody ShipmentOutBase shipmentOutBase){
        List<ShipmentOutBase> shipmentOutBaseList = shipmentOutBaseService.list(new QueryWrapper<>(shipmentOutBase));
        return shipmentOutBaseList.size() > 0?Result.success("查询成功").data(shipmentOutBaseList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ShipmentOutBase> page = shipmentOutBaseService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0?Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        ShipmentOutBase shipmentOutBase = shipmentOutBaseService.getById(id);
        return shipmentOutBase == null? Result.success("查询成功").data(shipmentOutBase) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody ShipmentOutBase shipmentOutBase){
        shipmentOutBase.setId(id);
        boolean b = shipmentOutBaseService.updateById(shipmentOutBase);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}