package com.dove.breed.controller;
import com.alibaba.excel.converters.shortconverter.ShortBooleanConverter;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.dto.ShipmentOutBillDto;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.service.DovecoteOutBillService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.ShipmentOutBillService;
import com.dove.breed.entity.ShipmentOutBill;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.WritePendingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 基地出库单 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "基地出库单")
@RestController
@RequestMapping("/breed/shipmentOutBill")
public class ShipmentOutBillController {

    @Autowired
    public ShipmentOutBillService shipmentOutBillService;
    @Autowired
    private ConvertUtil convertUtil;
    @Autowired
    private DovecoteOutBillService dovecoteOutBillService;


    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = shipmentOutBillService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody(required = false) ShipmentOutBillDto shipmentOutBillDto){
        ShipmentOutBill shipmentOutBill = new ShipmentOutBill();
        BeanUtils.copyProperties(shipmentOutBillDto,shipmentOutBill,ShipmentOutBill.class);
        List<ShipmentOutBill> shipmentOutBillList = shipmentOutBillService.list(new QueryWrapper<>(shipmentOutBill));
        List<ShipmentOutBillVo> shipmentOutBillVoList = convertUtil.convert(shipmentOutBillList,ShipmentOutBillVo.class);
        return shipmentOutBillList.size() > 0?Result.success("查询成功").data(shipmentOutBillVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ShipmentOutBill> page = shipmentOutBillService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<ShipmentOutBillVo> page1 = convertUtil.convert(page,ShipmentOutBillVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id){
        ShipmentOutBill shipmentOutBill = shipmentOutBillService.getById(id);
        ShipmentOutBillVo shipmentOutBillVo = convertUtil.convert(shipmentOutBill,ShipmentOutBillVo.class);
        return shipmentOutBill != null? Result.success("查询成功").data(shipmentOutBillVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody ShipmentOutBillDto shipmentOutBillDto){
        ShipmentOutBill shipmentOutBill = new ShipmentOutBill();
        BeanUtils.copyProperties(shipmentOutBillDto,shipmentOutBill,ShipmentOutBill.class);
        shipmentOutBill.setId(id);
        boolean b = shipmentOutBillService.updateById(shipmentOutBill);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }

//    @ApiOperation(value = "创建基地出库单")
//    @PostMapping("/submitBill")
//    public Result saveBill(@RequestBody ShipmentOutBillDto shipmentOutBillDto){
//        ShipmentOutBill bill = convertUtil.convert(shipmentOutBillDto, ShipmentOutBill.class);
//        int i = shipmentOutBillService.saveBill(bill);
//        return i != 0?Result.success("创建成功") : Result.error("创建失败");
//    }


}
