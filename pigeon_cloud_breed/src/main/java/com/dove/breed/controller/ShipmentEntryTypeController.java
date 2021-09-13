package com.dove.breed.controller;
import com.dove.breed.entity.dto.ShipmentEntryTypeDto;
import com.dove.breed.entity.vo.ShipmentEntryTypeVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.ShipmentEntryTypeService;
import com.dove.breed.entity.ShipmentEntryType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 基地进库类型表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "基地进库类型表")
@RestController
@RequestMapping("/breed/shipmentEntryType")
public class ShipmentEntryTypeController {

    @Autowired
    public ShipmentEntryTypeService shipmentEntryTypeService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ShipmentEntryTypeDto shipmentEntryTypeDto){
        ShipmentEntryType shipmentEntryType = new ShipmentEntryType();
        BeanUtils.copyProperties(shipmentEntryTypeDto,shipmentEntryType,ShipmentEntryType.class);
        shipmentEntryType.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = shipmentEntryTypeService.save(shipmentEntryType);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = shipmentEntryTypeService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody(required = false) ShipmentEntryTypeDto shipmentEntryTypeDto){
        ShipmentEntryType shipmentEntryType = new ShipmentEntryType();
        BeanUtils.copyProperties(shipmentEntryTypeDto,shipmentEntryType,ShipmentEntryType.class);
        List<ShipmentEntryType> shipmentEntryTypeList = shipmentEntryTypeService.list(new QueryWrapper<>(shipmentEntryType));
        List<ShipmentEntryTypeVo> shipmentEntryTypeVoList = convertUtil.convert(shipmentEntryTypeList,ShipmentEntryTypeVo.class);
        return shipmentEntryTypeList.size() > 0?Result.success("查询成功").data(shipmentEntryTypeVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ShipmentEntryType> page = shipmentEntryTypeService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<ShipmentEntryTypeVo> page1 = convertUtil.convert(page,ShipmentEntryTypeVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        ShipmentEntryType shipmentEntryType = shipmentEntryTypeService.getById(id);
        ShipmentEntryTypeVo shipmentEntryTypeVo = convertUtil.convert(shipmentEntryType,ShipmentEntryTypeVo.class);
        return shipmentEntryType != null? Result.success("查询成功").data(shipmentEntryTypeVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody ShipmentEntryTypeDto shipmentEntryTypeDto){
        ShipmentEntryType shipmentEntryType = new ShipmentEntryType();
        BeanUtils.copyProperties(shipmentEntryTypeDto,shipmentEntryType,ShipmentEntryType.class);
        shipmentEntryType.setTypeId(id);
        boolean b = shipmentEntryTypeService.updateById(shipmentEntryType);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "根据type获取typeName")
    @PostMapping("/getTypeNameByType")
    public Result getTypeNameByType(@RequestParam(value = "type")String type){
        List<ShipmentEntryTypeVo> typeVoList = shipmentEntryTypeService.getTypeNameByType(type);
        return typeVoList != null?Result.success("获取成功").data(typeVoList) : Result.error("获取失败");
    }

}
