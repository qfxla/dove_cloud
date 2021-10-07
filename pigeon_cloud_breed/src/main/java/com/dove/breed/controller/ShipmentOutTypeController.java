package com.dove.breed.controller;
import com.dove.breed.entity.dto.ShipmentOutTypeDto;
import com.dove.breed.entity.vo.ShipmentOutTypeVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.ShipmentOutTypeService;
import com.dove.breed.entity.ShipmentOutType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@CrossOrigin
@Slf4j
@Api(tags = "基地出库类型表")
@RestController
@RequestMapping("/breed/shipmentOutType")
public class ShipmentOutTypeController {

    @Autowired
    public ShipmentOutTypeService shipmentOutTypeService;

    @Autowired
    private ConvertUtil convertUtil;


    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = shipmentOutTypeService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody(required = false) ShipmentOutTypeDto shipmentOutTypeDto){
        ShipmentOutType shipmentOutType = new ShipmentOutType();
        BeanUtils.copyProperties(shipmentOutTypeDto,shipmentOutType,ShipmentOutType.class);
        List<ShipmentOutType> shipmentOutTypeList = shipmentOutTypeService.list(new QueryWrapper<>(shipmentOutType));
        List<ShipmentOutTypeVo> shipmentOutTypeVoList = convertUtil.convert(shipmentOutTypeList,ShipmentOutTypeVo.class);
        return shipmentOutTypeList.size() > 0?Result.success("查询成功").data(shipmentOutTypeVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ShipmentOutType> page = shipmentOutTypeService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<ShipmentOutTypeVo> page1 = convertUtil.convert(page,ShipmentOutTypeVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        ShipmentOutType shipmentOutType = shipmentOutTypeService.getById(id);
        ShipmentOutTypeVo shipmentOutTypeVo = convertUtil.convert(shipmentOutType,ShipmentOutTypeVo.class);
        return shipmentOutType != null? Result.success("查询成功").data(shipmentOutTypeVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody ShipmentOutTypeDto shipmentOutTypeDto){
        ShipmentOutType shipmentOutType = new ShipmentOutType();
        BeanUtils.copyProperties(shipmentOutTypeDto,shipmentOutType,ShipmentOutType.class);
        shipmentOutType.setTypeId(id);
        boolean b = shipmentOutTypeService.updateById(shipmentOutType);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }

//    @ApiOperation(value = "新增出库类型")
//    @PostMapping("/saveType")
//    public Result save(@RequestBody ShipmentOutTypeDto shipmentOutTypeDto){
//
//    }

}
