package com.dove.breed.controller;
import com.dove.breed.entity.dto.DovecoteEntryBillDto;
import com.dove.breed.entity.vo.DovecoteEntryBillVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.utils.ConvertUtil;
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

import java.util.Date;
import java.util.List;
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

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DovecoteEntryBillDto dovecoteEntryBillDto){
        DovecoteEntryBill dovecoteEntryBill = convertUtil.convert(dovecoteEntryBillDto, DovecoteEntryBill.class);
        boolean save = dovecoteEntryBillService.save(dovecoteEntryBill);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = dovecoteEntryBillService.removeById(id);
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

}
