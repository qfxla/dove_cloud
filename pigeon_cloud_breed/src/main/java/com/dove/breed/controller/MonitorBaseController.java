package com.dove.breed.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.MonitorBase;
import com.dove.breed.entity.dto.MonitorBaseDto;
import com.dove.breed.entity.vo.MonitorBaseVo;
import com.dove.breed.service.MonitorBaseService;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
/**
 * <p>
 * 摄像头信息 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@CrossOrigin
@Api(value = "摄像头接口", tags = "摄像头接口")
@Slf4j
@RestController
@RequestMapping("/breed/monitor-base")
public class MonitorBaseController {

    @Resource
    private MonitorBaseService monitorBaseService;

    @ApiOperation(value = "分页查寻所有摄像头")
    @GetMapping(value = "/{current}/{size}")
    public Result findAll(@PathVariable int current, @PathVariable int size){
        return Result.success().data(PageUtil.list2Page(monitorBaseService.list(SecurityContextUtil.getUserDetails().getEnterpriseId()),current,size));
    }

    @ApiOperation(value = "根据摄像头类型查询所有的摄像头")
    @GetMapping(value = "/baseId/{current}/{size}")
    public Result findByBaseId(@RequestParam("基地id")Long baseId,@RequestParam("摄像头类型(1基地，2鸽棚，3投喂机)") Integer type,
                               @RequestParam(value = "鸽棚编号(当类型为(3)投喂机时才可以选择是否填写，否则禁用)",required = false)String dovecoteNumber,
                               @RequestParam(value = "设备使用状态(1使用中，2未启用，3故障)",required = false)Integer statusCode,
                               @PathVariable int current,@PathVariable int size){
        List<MonitorBaseVo> list = monitorBaseService.listByType(baseId, type, dovecoteNumber, statusCode, SecurityContextUtil.getUserDetails().getEnterpriseId());
        return Result.success().data(PageUtil.list2Page(list, current, size));
    }

    @ApiOperation(value = "添加摄像头")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MonitorBaseDto monitorBaseDto){
        return monitorBaseService.add(monitorBaseDto) ? Result.success(): Result.error();
    }

    @ApiOperation(value = "根据id删除摄像头")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return monitorBaseService.removeById(id) ? Result.success(): Result.error();
    }

    @ApiOperation(value = "根据id查询摄像头信息")
    @GetMapping(value = "/{id}")
    public Result findByid(@PathVariable Long id){
        return Result.success().data(monitorBaseService.getVoById(id));
    }

    @ApiOperation(value = "根据id更新设备使用状态")
    @PostMapping(value = "/modify/{id}/{statusCode}")
    public Result modify(@PathVariable Long id, @PathVariable Integer statusCode){
        UpdateWrapper<MonitorBase> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(MonitorBase::getId,id);
        MonitorBase mv = new MonitorBase();
        mv.setStatusCode(statusCode);
        return monitorBaseService.update(mv,updateWrapper) ? Result.success(): Result.error();
    }

    @ApiOperation(value = "根据id更新Token")
    @PostMapping(value = "/updateToken/{id}")
    public Result updateToken(@PathVariable Long id){
        return monitorBaseService.updateToken(id) ? Result.success(): Result.error();
    }

    @ApiOperation(value = "更新摄像头信息")
    @PostMapping(value = "/update")
    public Result upData(@RequestBody MonitorBaseDto monitorBaseDto){
        monitorBaseService.upData(monitorBaseDto);
        MonitorBase vs = monitorBaseService.getById(monitorBaseDto.getId());
        return Result.success().data(vs);
    }

}