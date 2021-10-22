package com.dove.breed.controller;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.CageReal;
import com.dove.breed.entity.DovecoteOutBase;
import com.dove.breed.entity.dto.DovecoteOutBaseDto;
import com.dove.breed.entity.dto.DovecoteOutBillDto;
import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.entity.vo.ManualIncubationVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.service.DovecoteEntryBaseService;
import com.dove.breed.service.DovecoteOutBaseService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;


import com.dove.breed.service.DovecoteOutBillService;
import com.dove.breed.entity.DovecoteOutBill;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

/**
* <p>
    * 鸽棚出仓单 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "鸽棚出仓单")
@RestController
@RequestMapping("/breed/dovecoteOutBill")
public class DovecoteOutBillController {

    @Autowired
    public DovecoteOutBillService dovecoteOutBillService;
    @Autowired
    private DovecoteOutBaseService dovecoteOutBaseService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DovecoteOutBillDto dovecoteOutBillDto){
        DovecoteOutBill dovecoteOutBill = convertUtil.convert(dovecoteOutBillDto, DovecoteOutBill.class);
        boolean save = dovecoteOutBillService.save(dovecoteOutBill);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        QueryWrapper<DovecoteOutBill> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean b = dovecoteOutBillService.remove(wrapper);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody DovecoteOutBillDto dovecoteOutBillDto){
        DovecoteOutBill dovecoteOutBill = convertUtil.convert(dovecoteOutBillDto, DovecoteOutBill.class);
        List<DovecoteOutBill> dovecoteOutBillList = dovecoteOutBillService.list(new QueryWrapper<>(dovecoteOutBill));
        List<DovecoteOutBillVo> dovecoteOutBillVoList = convertUtil.convert(dovecoteOutBillList, DovecoteOutBillVo.class);
        return dovecoteOutBillVoList.size() > 0?Result.success("查询成功").data(dovecoteOutBillVoList) : Result.error("查询失败");
    }


//    @ApiOperation(value = "根据id修改")
//    @PostMapping("/update")
//    public Result update(@RequestParam("billId")Long billId,@RequestBody Map<String,Object> map){
//        //删除原订单号
//        dovecoteOutBillService.removeById(billId);
//        QueryWrapper<DovecoteOutBase> wrapper = new QueryWrapper<>();
//        wrapper.eq("dovecote_out_bill",billId)
//                .eq("is_deleted",0);
//        List<DovecoteOutBase> bases = dovecoteOutBaseService.list(wrapper);
//        ArrayList<Long> list1 = new ArrayList<>();
//        for (DovecoteOutBase base : bases) {
//            list1.add(base.getId());
//        }
//        dovecoteOutBaseService.removeByIds(list1);
//
//        DovecoteOutBillDto dovecoteOutBillDto = null;
//        ArrayList<DovecoteOutBaseDto> dovecoteOutBaseDtoList = new ArrayList<>();
//        try {
//            dovecoteOutBillDto = JSON.parseObject(JSON.toJSONString(map.get("dovecoteOutBillDto")), DovecoteOutBillDto.class);
//            List<DovecoteOutBaseDto> list = JSON.parseObject(JSON.toJSONString(map.get("dovecoteOutBaseDtoList")),ArrayList.class);
//            for (int i = 0;i < list.size();i++){
//                //数组内容得在解析一遍手动放进去
//                DovecoteOutBaseDto po = JSON.parseObject(JSON.toJSONString(list.get(i)), DovecoteOutBaseDto.class);
//                dovecoteOutBaseDtoList.add(po);
//            }
//        }catch (Exception exception){
//            exception.printStackTrace();
//        }
//        DovecoteOutBillVo dovecoteOutBillVo = dovecoteOutBillService.submitDovecoteOutBill(dovecoteOutBillDto,dovecoteOutBaseDtoList);
//        return dovecoteOutBillVo.getId() != null?Result.success("订单修改成功").data(dovecoteOutBillVo) : Result.error("订单修改失败");
//    }

    @ApiOperation(value = "根据创建时间和基地id查询ShipmentOutBill")
    @GetMapping("/findBillByGmt_createAndBaseId/{startTime}/{endTime}/{dovecoteId}")
    public Result findBillByGmt_createAndBaseId(@PathVariable("startTime") Date startTime, @PathVariable("endTime") Date endTime, @PathVariable("dovecoteId")Long dovecoteId){
        List<DovecoteOutBillVo> list = dovecoteOutBillService.findBillByGmt_createAndBaseId(startTime, endTime, dovecoteId);
        return list.size()>0?Result.success("查找成功").data(list):Result.error("查找失败");
    }

    @ApiOperation(value = "展示订单")
    @GetMapping("findBillByDovecoteAndType")
    public Result findBillByDovecoteAndType(@RequestParam("baseId")Long baseId,
                                            @RequestParam("dovecoteNumber")String dovecoteNumber,
                                            @RequestParam("type")String type,
                                            @RequestParam("pageNum")int pageNum,
                                            @RequestParam("pageSize")int pageSize){
        List<DovecoteOutBillVo> billList = dovecoteOutBillService.findBillByDovecoteAndType(baseId, dovecoteNumber, type);
        billList = billList.stream().sorted(Comparator.comparing(DovecoteOutBillVo::getGmtCreate).reversed()).collect(Collectors.toList());
        Page page = PageUtil.list2Page(billList, pageNum, pageSize);
        return Result.success("查询成功").data(page);
    }

//    @ApiOperation(value = "提交入库单")
//    @PostMapping("/submitDovecoteOutBill")
//    public Result submitDovecoteOutBill(@RequestBody Map<String,Object> map){
//        DovecoteOutBillDto dovecoteOutBillDto = null;
//        ArrayList<DovecoteOutBaseDto> dovecoteOutBaseDtoList = new ArrayList<>();
//        try {
//            dovecoteOutBillDto = JSON.parseObject(JSON.toJSONString(map.get("dovecoteOutBillDto")), DovecoteOutBillDto.class);
//            List<DovecoteOutBaseDto> list = JSON.parseObject(JSON.toJSONString(map.get("dovecoteOutBaseDtoList")),ArrayList.class);
//            for (int i = 0;i < list.size();i++){
//                //数组内容得在解析一遍手动放进去
//                DovecoteOutBaseDto po = JSON.parseObject(JSON.toJSONString(list.get(i)), DovecoteOutBaseDto.class);
//                dovecoteOutBaseDtoList.add(po);
//            }
//        }catch (Exception exception){
//            exception.printStackTrace();
//        }
//        DovecoteOutBillVo dovecoteOutBillVo = dovecoteOutBillService.submitDovecoteOutBill(dovecoteOutBillDto,dovecoteOutBaseDtoList);
//        return dovecoteOutBillVo != null?Result.success("提交成功").data(dovecoteOutBillVo) : Result.error("提交失败");
//    }

    //用于求某天基地各类型总数
    @ApiOperation("用于求某天基地各类型总数")
    @GetMapping("/sumAllDovecoteByTypeAndDay")
    public Result sumAllDovecoteByType(@RequestParam("baseId")Long baseId,@RequestParam("type")String type,
                                       @RequestParam("year")int year,@RequestParam("month")int month,@RequestParam("day")int day){
        Map<String, Integer> map = dovecoteOutBillService.getAllAmountByBaseIdAndDateAndType(baseId, type, year, month, day);
        return Result.success("获取成功").data(map);
    }

    //用于求某月基地各类型总数
    @ApiOperation("用于求某月基地各类型总数")
    @GetMapping("/sumAllDovecoteByTypeAndMonth")
    public Result sumAllDovecoteByTypeAndMonth(@RequestParam("baseId")Long baseId,@RequestParam("type")String type,
                                       @RequestParam("year")int year,@RequestParam("month")int month){
        Map<String, Integer> map = dovecoteOutBillService.getAllAmountByBaseIdAndMonthAndType(baseId, type, year, month);
        return Result.success("获取成功").data(map);
    }

    //用于求一年基地各月份各类型总数
    @ApiOperation("用于求一年中基地各月份各类型总数")
    @GetMapping("/getAllDovecoteByTypeAndYearOfMonth")
    public Result getAllDovecoteByTypeAndYearOfMonth(@RequestParam("baseId")Long baseId,
                                                     @RequestParam("type")String type,
                                                     @RequestParam("year")int year){
        List<Map<String, Integer>> list = dovecoteOutBillService.getAllDovecoteByTypeAndYearOfMonth(baseId, type, year);
        return list.size() != 0?Result.success("获取成功").data(list) : Result.error("获取失败");
    }

    @ApiOperation(value = "根据批次号查鸽棚出库单")
    @GetMapping("/getDovecoteOutBillByFarmBatch")
    public Result getDovecoteOutBillByFarmBatch(@RequestParam("farmBatch")String farmBatch,
                                                @RequestParam("baseId")Long baseId,
                                                @RequestParam("type")String type){
        QueryWrapper<DovecoteOutBill> wrapper = new QueryWrapper<>();
        wrapper.eq("farm_batch",farmBatch).eq("base_id",baseId).eq("type",type);
        List<DovecoteOutBill> list = dovecoteOutBillService.list(wrapper);
        return list.size() > 0?Result.success("查询成功").data(list) : Result.error("无该订单");
    }

    @ApiOperation(value = "根据日期查鸽棚出库单")
    @GetMapping("/getDovecoteOutBillByDate")
    public Result getDovecoteOutBillByDate(@RequestParam("date") String dateString,
                                           @RequestParam("baseId")Long baseId,
                                           @RequestParam("type")String type) throws ParseException {
        StringBuilder stringBuilder = new StringBuilder(dateString);
        int i = stringBuilder.indexOf("-");
        StringBuilder stringBuilder1 = stringBuilder.delete(i, i + 1);
        int i1 = stringBuilder1.indexOf("-");
        StringBuilder stringBuilder2 = stringBuilder1.delete(i1, i1 + 1);
        dateString = stringBuilder2.toString();

        QueryWrapper<DovecoteOutBill> wrapper = new QueryWrapper<>();
        wrapper.eq("farm_batch",dateString).eq("base_id",baseId).eq("type",type);
        List<DovecoteOutBill> list = dovecoteOutBillService.list(wrapper);
        return Result.success().data(list);
    }

    @ApiOperation(value = "月结求今年各月各类型总数")
    @GetMapping("/getSumOfTypeAndMonthByBaseId")
    public Result getSumOfTypeAndMonthByBaseId(@RequestParam("baseId")Long baseId){
        List<Map<String, Integer>> list = dovecoteOutBillService.getSumOfTypeAndMonthByBaseId(baseId);
        return Result.success("获取成功").data(list);
    }

    @ApiOperation(value = "用于求基地各天某类型各数量")
    @GetMapping("/getSumOfDayByBaseIdAndType")
    public Result getSumOfDayByBaseIdAndType(@RequestParam("baseId")Long baseId,@RequestParam("type")String type){
        List<Map<String, Integer>> list = dovecoteOutBillService.getEveryDaySumByType(baseId, type);
        return Result.success("获取成功").data(list);
    }


    @ApiOperation(value = "求该基地中出库订单，倒叙")
    @GetMapping("/getBillByBaseId")
    public Result getBillByBaseId(@RequestParam("baseId")Long baseId,@RequestParam("pageNum")int pageNum,
                                  @RequestParam("pageSize")int pageSize){
        QueryWrapper<DovecoteOutBill> wrapper = new QueryWrapper<>();
        wrapper.eq("base_id",baseId);
        List<DovecoteOutBill> list = dovecoteOutBillService.list(wrapper);
        List<DovecoteOutBillVo> list1 = convertUtil.convert(list, DovecoteOutBillVo.class);
        list1 = list1.stream().sorted(Comparator.comparing(DovecoteOutBillVo::getGmtCreate).reversed()).collect(Collectors.toList());
        Page page = PageUtil.list2Page(list1, pageNum, pageSize);
        return page.getSize() > 0? Result.success("获取成功").data(page) : Result.error("获取失败");
    }
}
