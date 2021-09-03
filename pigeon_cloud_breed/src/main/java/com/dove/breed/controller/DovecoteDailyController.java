package com.dove.breed.controller;


import com.dove.breed.entity.DovecoteDaily;
import com.dove.breed.entity.vo.DovecoteDailyVo;
import com.dove.breed.service.DovecoteDailyService;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 鸽棚日结表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-02
 */
@CrossOrigin
@Api(tags = "鸽棚日结表")
@RestController
@RequestMapping("/breed/dovecote-daily")
public class DovecoteDailyController {

    @Autowired
    private DovecoteDailyService dovecoteDailyService;

    @ApiOperation(value = "获取日结表数据")
    @GetMapping("/getDovecoteDaily")
    public Result getDovecoteDaily(@RequestParam("baseId")Long baseId, @RequestParam("dovecoteNumber")String dovecoteNumber,
                                   @RequestParam("year")Integer year, @RequestParam("month")Integer month, @RequestParam("day")Integer day){
        DovecoteDailyVo dovecoteDaily = dovecoteDailyService.getDovecoteDaily(baseId, dovecoteNumber, year, month, day);
        return dovecoteDaily != null? Result.success("获取成功").data(dovecoteDaily) : Result.error("获取失败");
    }
}

