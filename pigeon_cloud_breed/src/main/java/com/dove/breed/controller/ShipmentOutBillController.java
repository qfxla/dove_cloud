package com.dove.breed.controller;
import com.alibaba.excel.converters.shortconverter.ShortBooleanConverter;
import com.alibaba.fastjson.JSON;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.ShipmentOutBase;
import com.dove.breed.entity.dto.ShipmentOutBaseDto;
import com.dove.breed.entity.dto.ShipmentOutBillDto;
import com.dove.breed.entity.dto.ShipmentOutBillDto;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.entity.vo.ShipmentOutBillVo;
import com.dove.breed.service.DovecoteOutBillService;
import com.dove.breed.service.ShipmentEntryBaseService;
import com.dove.breed.service.ShipmentOutBaseService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
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
import java.util.*;

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
    @Autowired
    private ShipmentOutBaseService shipmentOutBaseService;


    @ApiOperation(value = "提交基地出库单")
    @PostMapping("/submit")
    public Result submit(@RequestBody ShipmentOutBillDto shipmentOutBillDto){
        int i = shipmentOutBillService.saveBill(shipmentOutBillDto);
        return i == 1?Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "展示基地出库单")
    @GetMapping("/getShipmentOutBill")
    public Result getShipmentOutBill(@RequestParam("baseId")Long baseId,@RequestParam("type")String type,
                                     @RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        Page<ShipmentOutBillVo> page = shipmentOutBillService.getShipmentOutBill(baseId, type, pageNum, pageSize);
        return Result.success("获取成功").data(page);
    }

    @ApiOperation(value = "查某天的基地出库单")
    @GetMapping("/getShipmentOutBillByDate")
    public Result getShipmentOutBillByDate(@RequestParam("baseId")Long baseId,
                                            @RequestParam("type")String type,
                                           @RequestParam("date") @ApiParam(value = "格式为2000-10-10字符串") Date date,
                                           @RequestParam("pageNum")int pageNum,
                                           @RequestParam("pageSize")int pageSize){
        Page<ShipmentOutBillVo> page = shipmentOutBillService.getShipmentOutBillByDate(baseId, type, date, pageNum, pageSize);
        return Result.success("获取成功").data(page);
    }
}
