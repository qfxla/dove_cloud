package com.dove.breed.controller;
import com.alibaba.fastjson.JSON;

import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.dto.DovecoteEntryBaseDto;
import com.dove.breed.entity.dto.DovecoteEntryBillDto;

import com.dove.breed.entity.dto.DovecoteOutBaseDto;
import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.service.DovecoteEntryBaseService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;


import com.dove.breed.service.DovecoteEntryBillService;
import com.dove.breed.entity.DovecoteEntryBill;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 鸽棚入仓单 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "鸽棚入仓单")
@RestController
@RequestMapping("/breed/dovecoteEntryBill")
public class DovecoteEntryBillController {

    @Autowired
    public DovecoteEntryBillService dovecoteEntryBillService;

    @Autowired
    private ConvertUtil convertUtil;

    @Autowired
    private DovecoteEntryBaseService dovecoteEntryBaseService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestParam("billId")Long billId,@RequestBody Map<String,Object> map){
        //删除原订单号
        dovecoteEntryBillService.removeById(billId);
        QueryWrapper<DovecoteEntryBill> wrapper = new QueryWrapper<>();
        wrapper.eq("dovecote_out_bill",billId).eq("is_deleted",0);
        List<DovecoteEntryBill> bases = dovecoteEntryBillService.list(wrapper);
        ArrayList<Long> list1 = new ArrayList<>();
        for (DovecoteEntryBill base : bases) {
            list1.add(base.getId());
        }
        dovecoteEntryBaseService.removeById(list1);
        DovecoteEntryBillDto dovecoteEntryBillDto = null;
        ArrayList<DovecoteEntryBaseDto> dovecoteEntryBaseDtoList = new ArrayList<>();
        try {
            dovecoteEntryBillDto = JSON.parseObject(JSON.toJSONString(map.get("dovecoteEntryBillDto")),DovecoteEntryBillDto.class);
            List<DovecoteEntryBaseDto> list = JSON.parseObject(JSON.toJSONString(map.get("dovecoteEntryBaseDtoList")), ArrayList.class);
            for (int i = 0;i < list.size();i++){
                DovecoteEntryBaseDto po = JSON.parseObject(JSON.toJSONString(list.get(i)), DovecoteEntryBaseDto.class);
                dovecoteEntryBaseDtoList.add(po);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        DovecoteEntryBillVo dovecoteEntryBillVo = dovecoteEntryBillService.submitDovecoteEntryBill(dovecoteEntryBillDto, dovecoteEntryBaseDtoList);

        return dovecoteEntryBillVo.getId() != null? Result.success("订单修改成功").data(dovecoteEntryBillVo) : Result.error("订单修改失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        QueryWrapper<DovecoteEntryBill> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean b = dovecoteEntryBillService.remove(wrapper);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody DovecoteEntryBillDto dovecoteEntryBillDto){
        DovecoteEntryBill dovecoteEntryBill = convertUtil.convert(dovecoteEntryBillDto, DovecoteEntryBill.class);
        List<DovecoteEntryBill> dovecoteEntryBillList = dovecoteEntryBillService.list(new QueryWrapper<>(dovecoteEntryBill));
        List<DovecoteEntryBillVo> dovecoteEntryBillVoList = convertUtil.convert(dovecoteEntryBillList, DovecoteEntryBillVo.class);
        return dovecoteEntryBillVoList.size() > 0?Result.success("查询成功").data(dovecoteEntryBillVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<DovecoteEntryBill> page = dovecoteEntryBillService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<DovecoteEntryBillVo> page1 = convertUtil.convert(page, DovecoteEntryBillVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        DovecoteEntryBill dovecoteEntryBill = dovecoteEntryBillService.getById(id);
        DovecoteEntryBillVo dovecoteEntryBillVo = convertUtil.convert(dovecoteEntryBill, DovecoteEntryBillVo.class);
        return dovecoteEntryBillVo != null? Result.success("查询成功").data(dovecoteEntryBillVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody DovecoteEntryBillDto dovecoteEntryBillDto){
        DovecoteEntryBill dovecoteEntryBill = convertUtil.convert(dovecoteEntryBillDto, DovecoteEntryBill.class);
        dovecoteEntryBill.setId(id);
        boolean b = dovecoteEntryBillService.updateById(dovecoteEntryBill);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "根据创建时间和基地id查询ShipmentOutBill")
    @GetMapping("/findBillByGmt_createAndBaseId/{startTime}/{endTime}/{dovecoteId}")
    public Result findBillByGmt_createAndBaseId(@PathVariable("startTime") Date startTime, @PathVariable("endTime") Date endTime, @PathVariable("dovecoteId")Long dovecoteId){
        List<DovecoteEntryBillVo> list = dovecoteEntryBillService.findBillByGmt_createAndBaseId(startTime, endTime, dovecoteId);
        return list.size()>0?Result.success("查找成功").data(list):Result.error("查找失败");
    }

    @ApiOperation(value = "展示订单")
    @GetMapping("findBillByDovecoteAndType")
    public Result findBillByDovecoteAndType(@RequestParam("baseId")Long baseId,
                                            @RequestParam("dovecoteNumber")String dovecoteNumber,
                                            @RequestParam("type")String type,
                                            @RequestParam("pageNum")int pageNum,
                                            @RequestParam("pageSize")int pageSize){
        List<DovecoteEntryBillVo> billList = dovecoteEntryBillService.findBillByDovecoteAndType(baseId, dovecoteNumber, type);
        billList = billList.stream().sorted(Comparator.comparing(DovecoteEntryBillVo::getGmtCreate).reversed()).collect(Collectors.toList());
        Page page = PageUtil.list2Page(billList, pageNum, pageSize);
        return Result.success("查询成功").data(page);
    }

    @ApiOperation(value = "提交出库单")
    @PostMapping("/submitDovecoteOutBill")
    public Result submitDovecoteOutBill(@RequestBody Map<String,Object> map){
        DovecoteEntryBillDto dovecoteEntryBillDto = null;
        ArrayList<DovecoteEntryBaseDto> dovecoteEntryBaseDtoList = new ArrayList<>();
        try {
            dovecoteEntryBillDto = JSON.parseObject(JSON.toJSONString(map.get("dovecoteEntryBillDto")), DovecoteEntryBillDto.class);
            List<DovecoteEntryBaseDto> list = JSON.parseObject(JSON.toJSONString(map.get("dovecoteEntryBaseDtoList")),ArrayList.class);
            for (int i = 0;i < list.size();i++){
                //数组内容得在解析一遍手动放进去
                DovecoteEntryBaseDto po = JSON.parseObject(JSON.toJSONString(list.get(i)), DovecoteEntryBaseDto.class);
                dovecoteEntryBaseDtoList.add(po);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        DovecoteEntryBillVo dovecoteEntryBillVo = dovecoteEntryBillService.submitDovecoteEntryBill(dovecoteEntryBillDto, dovecoteEntryBaseDtoList);
        return Result.success("提交成功").data(dovecoteEntryBillVo);
    }

    @ApiOperation(value = "展示鸽棚入仓单(分页)")
    @GetMapping("/getAllOrder/{pageNum}/{pageSize}")
    public Result getAllOrder(@PathVariable("pageNum")int pageNum,
                              @PathVariable("pageSize")int pageSize,
                              @RequestParam("baseId")Long baseId,
                              @RequestParam(value = "dovecoteNumber",required = false)String dovecoteNumber,
                              @RequestParam(value = "startTime",required = false)String startTime,
                              @RequestParam(value = "overTime",required = false)String overTime){
        IPage<DovecoteEntryBill> page = dovecoteEntryBillService.getAllOrder(pageNum,pageSize,baseId,dovecoteNumber,startTime,overTime);

        IPage<DovecoteEntryBillVo> page1 = convertUtil.convert(page, DovecoteEntryBillVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "根据baseId和type展示订单")
    @GetMapping("/getAllEntryByIdAndType/{pageNum}/{pageSize}")
    public Result getAllEntryByIdAndType(@PathVariable("pageNum")int pageNum,
                                         @PathVariable("pageSize")int pageSize,
                                         @RequestParam("baseId")Long basesId,
                                         @RequestParam("type")String type){
        List<DovecoteEntryBill> list = dovecoteEntryBillService.getAllEntryByIdAndType(basesId, type);
        List<DovecoteEntryBillVo> listVo = convertUtil.convert(list, DovecoteEntryBillVo.class);
        Page page = PageUtil.list2Page(listVo, pageNum, pageSize);
        return Result.success("获取成功").data(page);
    }

    //用于求某天基地各类型总数
    @ApiOperation("用于求某天基地各类型总数")
    @GetMapping("/sumAllDovecoteByTypeAndDay")
    public Result sumAllDovecoteByType(@RequestParam("baseId")Long baseId,@RequestParam("type")String type,
                                       @RequestParam("year")int year,@RequestParam("month")int month,@RequestParam("day")int day){
        Map<String, Integer> map = dovecoteEntryBillService.getAllAmountByBaseIdAndDateAndType(baseId, type, year, month, day);
        return Result.success("获取成功").data(map);
    }
}
