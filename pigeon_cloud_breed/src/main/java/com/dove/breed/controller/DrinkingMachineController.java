package com.dove.breed.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.DrinkingMachine;
import com.dove.breed.entity.dto.DrinkingMachineDto;
import com.dove.breed.entity.vo.DrinkingMachineVo;
import com.dove.breed.service.DrinkingMachineService;
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
 *  前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-11-16
 */
@CrossOrigin
@Slf4j
@Api(tags = "饮水机信息表")
@RestController
@RequestMapping("/breed/drinking-machine")
public class DrinkingMachineController {
    private ConvertUtil convertUtil = new ConvertUtil();

    @Autowired
    public DrinkingMachineService drinkingMachineService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DrinkingMachineDto drinkingMachineDto){
        DrinkingMachine convert = convertUtil.convert(drinkingMachineDto, DrinkingMachine.class);
        convert.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = drinkingMachineService.save(convert);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = drinkingMachineService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/deleteList")
    public Result delete(@RequestParam("idList") List idList){
        boolean b = drinkingMachineService.removeByIds(idList);
        return b ? Result.success("批量删除成功") : Result.error("批量删除失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{current}/{size}")
    public Object list(@RequestParam(value = "baseId", required = false) Long baseId,
                       @RequestParam(value = "dovecoteNumber", required = false) String dovecoteNumber,
                       @RequestParam(value = "open", required = false) Integer open,
                       @PathVariable(value = "current")Integer current, @PathVariable("size")Integer size){
        List<DrinkingMachineVo> list = drinkingMachineService.listByType(baseId, dovecoteNumber, open, SecurityContextUtil.getUserDetails().getEnterpriseId());
        Page page = PageUtil.list2Page(list, current, size);
        return page.getTotal() > 0?Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id){
        DrinkingMachine drinkingMachine = drinkingMachineService.getById(id);
        DrinkingMachineVo drinkingMachineVo = convertUtil.convert(drinkingMachine, DrinkingMachineVo.class);
        return drinkingMachineVo != null? Result.success("查询成功").data(drinkingMachineVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody DrinkingMachineDto drinkingMachineDto){
        DrinkingMachine drinkingMachine = convertUtil.convert(drinkingMachineDto, DrinkingMachine.class);
        drinkingMachine.setId(id);
        boolean b = drinkingMachineService.updateById(drinkingMachine);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "开机")
    @PostMapping("/open")
    public Result open(@RequestParam(value = "machineNumber") String machineNumber){
        boolean open = drinkingMachineService.open(machineNumber);
        return open? Result.success("启动成功") : Result.error("启动失败");
    }

    @ApiOperation(value = "关机")
    @PostMapping("/shutdown")
    public Result shutdown(@RequestParam(value = "machineNumber") String machineNumber,
                           @RequestParam("operator") String operator){
        boolean shutdown = drinkingMachineService.shutdown(machineNumber,operator);
        return shutdown? Result.success("关机成功") : Result.error("关机失败");
    }
}

