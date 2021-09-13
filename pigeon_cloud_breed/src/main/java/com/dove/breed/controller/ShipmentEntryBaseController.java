package com.dove.breed.controller;
import com.dove.breed.entity.dto.ShipmentEntryBaseDto;
import com.dove.breed.entity.vo.ShipmentEntryBaseVo;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.ShipmentEntryBaseService;
import com.dove.breed.entity.ShipmentEntryBase;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;
import org.xmlunit.util.Convert;

/**
* <p>
    * 基地进库信息表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "基地进库信息表")
@RestController
@RequestMapping("/breed/shipmentEntryBase")
public class ShipmentEntryBaseController {

    @Autowired
    public ShipmentEntryBaseService shipmentEntryBaseService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ShipmentEntryBaseDto shipmentEntryBaseDto){
        ShipmentEntryBase shipmentEntryBase = new ShipmentEntryBase();
        BeanUtils.copyProperties(shipmentEntryBaseDto,shipmentEntryBase,ShipmentEntryBase.class);
        shipmentEntryBase.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = shipmentEntryBaseService.save(shipmentEntryBase);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = shipmentEntryBaseService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody(required = false) ShipmentEntryBaseDto shipmentEntryBaseDto){
        ShipmentEntryBase shipmentEntryBase = new ShipmentEntryBase();
        BeanUtils.copyProperties(shipmentEntryBaseDto,shipmentEntryBase,ShipmentEntryBase.class);
        List<ShipmentEntryBase> shipmentEntryBaseList = shipmentEntryBaseService.list(new QueryWrapper<>(shipmentEntryBase));
        List<ShipmentEntryBaseVo> shipmentEntryBaseVoList = convertUtil.convert(shipmentEntryBaseList,ShipmentEntryBaseVo.class);
        return shipmentEntryBaseList.size() > 0?Result.success("查询成功").data(shipmentEntryBaseVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ShipmentEntryBase> page = shipmentEntryBaseService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<ShipmentEntryBaseVo> page1 = convertUtil.convert(page,ShipmentEntryBaseVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        ShipmentEntryBase shipmentEntryBase = shipmentEntryBaseService.getById(id);
        ShipmentEntryBaseVo shipmentEntryBaseVo = convertUtil.convert(shipmentEntryBase,ShipmentEntryBaseVo.class);
        return shipmentEntryBase != null? Result.success("查询成功").data(shipmentEntryBaseVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody ShipmentEntryBaseDto shipmentEntryBaseDto){
        ShipmentEntryBase shipmentEntryBase = new ShipmentEntryBase();
        BeanUtils.copyProperties(shipmentEntryBaseDto,shipmentEntryBase,ShipmentEntryBase.class);
        shipmentEntryBase.setId(id);
        boolean b = shipmentEntryBaseService.updateById(shipmentEntryBase);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
