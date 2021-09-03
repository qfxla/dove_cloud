package com.dove.breed.controller;
import com.dove.breed.entity.dto.ShipmentOutBaseDto;
import com.dove.breed.entity.vo.ShipmentOutBaseVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.ShipmentOutBaseService;
import com.dove.breed.entity.ShipmentOutBase;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@CrossOrigin
@Slf4j
@Api(tags = "基地出库信息表")
@RestController
@RequestMapping("/breed/shipmentOutBase")
public class ShipmentOutBaseController {

    @Autowired
    public ShipmentOutBaseService shipmentOutBaseService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ShipmentOutBaseDto shipmentOutBaseDto){
        ShipmentOutBase shipmentOutBase = new ShipmentOutBase();
        BeanUtils.copyProperties(shipmentOutBaseDto,shipmentOutBase,ShipmentOutBase.class);
        boolean save = shipmentOutBaseService.save(shipmentOutBase);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = shipmentOutBaseService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody(required = false) ShipmentOutBaseDto shipmentOutBaseDto){
        ShipmentOutBase shipmentOutBase = new ShipmentOutBase();
        BeanUtils.copyProperties(shipmentOutBaseDto,shipmentOutBase,ShipmentOutBase.class);
        List<ShipmentOutBase> shipmentOutBaseList = shipmentOutBaseService.list(new QueryWrapper<>(shipmentOutBase));
        List<ShipmentOutBaseVo> shipmentOutBaseVoList = convertUtil.convert(shipmentOutBaseList,ShipmentOutBaseVo.class);
        return shipmentOutBaseList.size() > 0?Result.success("查询成功").data(shipmentOutBaseVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ShipmentOutBase> page = shipmentOutBaseService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<ShipmentOutBaseVo> page1 = convertUtil.convert(page,ShipmentOutBaseVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id){
        ShipmentOutBase shipmentOutBase = shipmentOutBaseService.getById(id);
        ShipmentOutBaseVo shipmentOutBaseVo = convertUtil.convert(shipmentOutBase,ShipmentOutBaseVo.class);
        return shipmentOutBase != null? Result.success("查询成功").data(shipmentOutBaseVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody ShipmentOutBaseDto shipmentOutBaseDto){
        ShipmentOutBase shipmentOutBase = new ShipmentOutBase();
        BeanUtils.copyProperties(shipmentOutBaseDto,shipmentOutBase,ShipmentOutBase.class);
        shipmentOutBase.setId(id);
        boolean b = shipmentOutBaseService.updateById(shipmentOutBase);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }

}
