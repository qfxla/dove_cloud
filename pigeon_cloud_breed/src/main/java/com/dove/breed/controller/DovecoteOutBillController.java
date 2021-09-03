package com.dove.breed.controller;
import com.dove.breed.entity.dto.DovecoteOutBillDto;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.DovecoteOutBillService;
import com.dove.breed.entity.DovecoteOutBill;
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
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DovecoteOutBillDto dovecoteOutBillDto){
        DovecoteOutBill dovecoteOutBill = convertUtil.convert(dovecoteOutBillDto, DovecoteOutBill.class);
        boolean save = dovecoteOutBillService.save(dovecoteOutBill);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = dovecoteOutBillService.removeById(id);
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

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<DovecoteOutBill> page = dovecoteOutBillService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<DovecoteOutBillVo> page1 = convertUtil.convert(page, DovecoteOutBillVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        DovecoteOutBill dovecoteOutBill = dovecoteOutBillService.getById(id);
        DovecoteOutBillVo dovecoteOutBillVo = convertUtil.convert(dovecoteOutBill, DovecoteOutBillVo.class);
        return dovecoteOutBillVo != null? Result.success("查询成功").data(dovecoteOutBillVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody DovecoteOutBillDto dovecoteOutBillDto){
        DovecoteOutBill dovecoteOutBill = convertUtil.convert(dovecoteOutBillDto, DovecoteOutBill.class);
        dovecoteOutBill.setId(id);
        boolean b = dovecoteOutBillService.updateById(dovecoteOutBill);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "根据创建时间和基地id查询ShipmentOutBill")
    @GetMapping("/findBillByGmt_createAndBaseId/{startTime}/{endTime}/{dovecoteId}")
    public Result findBillByGmt_createAndBaseId(@PathVariable("startTime") Date startTime, @PathVariable("endTime") Date endTime, @PathVariable("dovecoteId")Long dovecoteId){
        List<DovecoteOutBillVo> list = dovecoteOutBillService.findBillByGmt_createAndBaseId(startTime, endTime, dovecoteId);
        return list.size()>0?Result.success("查找成功").data(list):Result.error("查找失败");
    }

}
