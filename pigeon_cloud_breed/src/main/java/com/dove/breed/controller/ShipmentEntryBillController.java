package com.dove.breed.controller;
import com.alibaba.fastjson.JSON;
import com.dove.breed.entity.ShipmentEntryBase;
import com.dove.breed.entity.dto.ShipmentEntryBaseDto;
import com.dove.breed.entity.dto.ShipmentEntryBillDto;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.entity.vo.ShipmentEntryBillVo;
import com.dove.breed.service.ShipmentEntryBaseService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;


import com.dove.breed.service.ShipmentEntryBillService;
import com.dove.breed.entity.ShipmentEntryBill;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 基地进库单 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "基地进库单")
@RestController
@RequestMapping("/breed/shipmentEntryBill")
public class ShipmentEntryBillController {

    @Autowired
    public ShipmentEntryBillService shipmentEntryBillService;

    @Autowired
    private ConvertUtil convertUtil;

    @Autowired
    private ShipmentEntryBaseService shipmentEntryBaseService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ShipmentEntryBillDto shipmentEntryBillDto){
        ShipmentEntryBill shipmentEntryBill = new ShipmentEntryBill();
        BeanUtils.copyProperties(shipmentEntryBillDto,shipmentEntryBill,ShipmentEntryBill.class);
        boolean save = shipmentEntryBillService.save(shipmentEntryBill);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/deletedBill")
    public Result deletedBill(@RequestParam("billId") Long billId){
        int i = shipmentEntryBillService.deletedBill(billId);
        return i == 1 ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody(required = false) ShipmentEntryBillDto shipmentEntryBillDto){
        ShipmentEntryBill shipmentEntryBill = new ShipmentEntryBill();
        BeanUtils.copyProperties(shipmentEntryBillDto,shipmentEntryBill,ShipmentEntryBill.class);
        List<ShipmentEntryBill> shipmentEntryBillList = shipmentEntryBillService.list(new QueryWrapper<>(shipmentEntryBill));
        List<ShipmentEntryBillVo> shipmentEntryBillVos = convertUtil.convert(shipmentEntryBillList,ShipmentEntryBillVo.class);
        return shipmentEntryBillList.size() > 0?Result.success("查询成功").data(shipmentEntryBillVos) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ShipmentEntryBill> page = shipmentEntryBillService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<ShipmentEntryBillVo> page1 = convertUtil.convert(page,ShipmentEntryBillVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        ShipmentEntryBill shipmentEntryBill = shipmentEntryBillService.getById(id);
        ShipmentEntryBillVo shipmentEntryBillVo = convertUtil.convert(shipmentEntryBill,ShipmentEntryBillVo.class);
        return shipmentEntryBill != null? Result.success("查询成功").data(shipmentEntryBillVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody Map<String,Object> map){
        //删除原订单号
        shipmentEntryBillService.removeById(id);
        QueryWrapper<ShipmentEntryBase> wrapper = new QueryWrapper<>();
        wrapper.eq("shipment_entry_bill",id).eq("is_deleted",0);
        List<ShipmentEntryBase> bases = shipmentEntryBaseService.list(wrapper);
        ArrayList<Long> list1 = new ArrayList<>();
        for (ShipmentEntryBase base : bases) {
            list1.add(base.getId());
        }
        shipmentEntryBaseService.removeByIds(list1);
        ShipmentEntryBillDto shipmentEntryBillDto = null;
        ArrayList<ShipmentEntryBaseDto> shipmentEntryBaseDtoList = new ArrayList<>();
        try {
            shipmentEntryBillDto = JSON.parseObject(JSON.toJSONString(map.get("shipmentEntryBillDto")),ShipmentEntryBillDto.class);
            List<ShipmentEntryBaseDto> list = JSON.parseObject(JSON.toJSONString(map.get("shipmentEntryBaseDtoList")),ArrayList.class);
            for(int i = 0;i<list.size();i++){
                //数组内容得再解析一遍手动放进去
                ShipmentEntryBaseDto po = JSON.parseObject(JSON.toJSONString(list.get(i)),ShipmentEntryBaseDto.class);
                shipmentEntryBaseDtoList.add(po);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ShipmentEntryBillVo shipmentEntryBillVo = shipmentEntryBillService.submitShipmentEntryBill(shipmentEntryBillDto, shipmentEntryBaseDtoList);
        return shipmentEntryBillVo.getId() != null?Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "根据创建时间和基地id查询ShipmentEntryBill")
    @GetMapping("/findBillByGmt_createAndShipmentId/{startTime}/{endTime}/{shipmentId}")
    public Result findBillByGmt_createAndShipmentId(@PathVariable("startTime") Date startTime, @PathVariable("endTime") Date endTime, @PathVariable("shipmentId")Long baseId){
        List<ShipmentEntryBillVo> list = shipmentEntryBillService.findBillByGmt_createAndShipmentId(startTime, endTime,baseId);
        return list.size()>0?Result.success("查询成功").data(list) : Result.error("查询失败");
    }

    @ApiOperation(value = "展示该基地进库订单")
    @GetMapping("/getAllEntryBillByIdAndType/{pageNum}/{pageSize}")
    public Result getAllEntryBillByIdAndType(@RequestParam("baseId")Long baseId,
                                             @RequestParam("type")String type,
                                             @PathVariable("pageNum")int pageNum,
                                             @PathVariable("pageSize")int pageSize){
        QueryWrapper<ShipmentEntryBill> wrapper = new QueryWrapper();
        wrapper.eq("base_id",baseId).
                eq("type",type).
                eq("is_deleted",0);
        List<ShipmentEntryBill> list = shipmentEntryBillService.list(wrapper);
        List<ShipmentEntryBillVo> shipmentEntryBillVoList = convertUtil.convert(list, ShipmentEntryBillVo.class);
        shipmentEntryBillVoList = shipmentEntryBillVoList.stream().sorted(Comparator.comparing(ShipmentEntryBillVo::getGmtCreate).reversed()).collect(Collectors.toList());
        Page page = PageUtil.list2Page(shipmentEntryBillVoList, pageNum, pageSize);
        return Result.success("查看成功").data(page);
    }

    @ApiOperation(value = "提交入库单,shipmentEntryBillDto,shipmentEntryBaseDtoList")
    @PostMapping("/submitShipmentEntryBill")
    public Result submitShipmentEntryBill(@RequestBody Map<String,Object> map){

        ShipmentEntryBillDto shipmentEntryBillDto = null;
        List<ShipmentEntryBaseDto> shipmentEntryBaseDtoList = new ArrayList<>();
        try {
            shipmentEntryBillDto = JSON.parseObject(JSON.toJSONString(map.get("shipmentEntryBillDto")),ShipmentEntryBillDto.class);
            List<ShipmentEntryBaseDto> list = JSON.parseObject(JSON.toJSONString(map.get("shipmentEntryBaseDtoList")),ArrayList.class);
            for(int i = 0;i<list.size();i++){
                //数组内容得再解析一遍手动放进去
                ShipmentEntryBaseDto po = JSON.parseObject(JSON.toJSONString(list.get(i)),ShipmentEntryBaseDto.class);
                shipmentEntryBaseDtoList.add(po);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        ShipmentEntryBillVo shipmentEntryBillVo = shipmentEntryBillService.submitShipmentEntryBill(shipmentEntryBillDto, shipmentEntryBaseDtoList);
        return shipmentEntryBillVo != null? Result.success("提交成功").data(shipmentEntryBillVo) : Result.error("提交失败");
    }
}
