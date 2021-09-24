package com.dove.breed.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.MonitorBase;
import com.dove.breed.service.MonitorBaseService;
import com.dove.entity.Result;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @ApiOperation(value = "分页查寻所有基地摄像头")
    @GetMapping(value = "/{current}/{size}")
    public Result findAll(@PathVariable int current, @PathVariable int size){
        IPage<MonitorBase> page = new Page<>(current,size);
        QueryWrapper<MonitorBase> wrapper = new QueryWrapper<>();
        wrapper.eq("guige", SecurityContextUtil.getUserDetails().getEnterpriseId())
                .eq("type","基地");
        return Result.success().data(monitorBaseService.page(page,wrapper));
    }

    @ApiOperation(value = "根据基地编号查询所有鸽棚的摄像头")
    @GetMapping(value = "/baseId/{baseId}/{current}/{size}")
    public Result findByBaseId(@PathVariable Long baseId,@PathVariable int current,@PathVariable int size){
        QueryWrapper<MonitorBase> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("base_id",baseId)
                .eq("type","鸽棚");
        IPage<MonitorBase> page = new Page<>(current,size);
        return Result.success().data(monitorBaseService.page(page,queryWrapper));
    }

    @ApiOperation(value = "根据鸽棚编号查询所有投喂机的摄像头")
    @GetMapping(value = "/breed//{current}/{size}")
    public Result findByBreed(@RequestParam("baseId")Long baseId,
                              @RequestParam("dovecoteNumber")String dovecoteNumber,
                              @PathVariable int current,@PathVariable int size){
        QueryWrapper<MonitorBase> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("base_id",baseId)
                .eq("dovecote_number",dovecoteNumber)
                .eq("type","投喂机");
        IPage<MonitorBase> page = new Page<>(current,size);
        return Result.success().data(monitorBaseService.page(page,queryWrapper));
    }

    @ApiOperation(value = "添加摄像头")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MonitorBase monitorVideo){
        return monitorBaseService.add(monitorVideo) ? Result.success(): Result.error();
    }

    @ApiOperation(value = "根据id删除摄像头")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return monitorBaseService.removeById(id) ? Result.success(): Result.error();
    }

    @ApiOperation(value = "根据id查询摄像头信息")
    @GetMapping(value = "/{id}")
    public Result findByid(@PathVariable String id){
        return Result.success().data(monitorBaseService.getById(id));
    }

    @ApiOperation(value = "根据id更新设备使用状态")
    @PostMapping(value = "/modify/{id}/{statusCode}")
    public Result modify(@PathVariable Long id, @PathVariable String statusCode){
        UpdateWrapper<MonitorBase> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(MonitorBase::getId,id);
        MonitorBase mv = new MonitorBase();
        mv.setStatusCode(statusCode);
        return monitorBaseService.update(mv,updateWrapper) ? Result.success(): Result.error();
    }

    @ApiOperation(value = "更新摄像头信息")
    @PostMapping(value = "/update")
    public Result upData(@RequestBody MonitorBase monitorVideo){
        monitorBaseService.upData(monitorVideo);
        MonitorBase vs = monitorBaseService.getById(monitorVideo.getId());
        return Result.success().data(vs);
    }

}