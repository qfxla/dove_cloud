package com.dove.breed.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.Drinking;
import com.dove.breed.entity.dto.DrinkingDto;
import com.dove.breed.entity.vo.DrinkingVo;
import com.dove.breed.service.DrinkingService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 饮水信息表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-10-12
 */

@Slf4j
@Api(tags = "饮水信息表")
@RestController
@RequestMapping("/breed/drinking")
public class DrinkingController {

    private ConvertUtil convertUtil = new ConvertUtil();

    @Autowired
    public DrinkingService drinkingService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DrinkingDto drinkingDto){
        Drinking convert = convertUtil.convert(drinkingDto, Drinking.class);
        convert.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = drinkingService.save(convert);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = drinkingService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{current}/{size}")
    public Object list(@RequestParam(value = "baseId", required = false) Long baseId,
                       @RequestParam(value = "dovecoteNumber", required = false) String dovecoteNumber,
                       @RequestParam(value = "operator", required = false) String operator,
                       @RequestParam(value = "startTime",required = false) String startTime,
                       @RequestParam(value = "endTime",required = false)String endTime,
                       @PathVariable("current")Integer current, @PathVariable("size")Integer size){
        List<DrinkingVo> list = drinkingService.listByType(baseId, dovecoteNumber, operator,startTime, endTime, SecurityContextUtil.getUserDetails().getEnterpriseId());
        Page page = PageUtil.list2Page(list, current, size);
        return page.getTotal() > 0?Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "获取所有操作人")
    @GetMapping("/getAllOperator")
    public Result getAllOperator(){
        List<String> operatorList = drinkingService.getAllOperator();
        return operatorList != null? Result.success("查询成功").data(operatorList) : Result.error("查询失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id){
        Drinking drinking = drinkingService.getById(id);
        DrinkingVo drinkingVo = convertUtil.convert(drinking, DrinkingVo.class);
        return drinkingVo != null? Result.success("查询成功").data(drinkingVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody DrinkingDto drinkingDto){
        Drinking drinking = convertUtil.convert(drinkingDto, Drinking.class);
        drinking.setId(id);
        boolean b = drinkingService.updateById(drinking);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}