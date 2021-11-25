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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "根据摄像头类型查询所有的摄像头")
    @GetMapping(value = "/baseId/{current}/{size}")
    public Result findByBaseId(@RequestParam(value = "baseId",required = false)Long baseId,
                               @RequestParam(value = "type",required = false) Integer type,
                               @RequestParam(value = "dovecoteNumber",required = false)String dovecoteNumber,
                               @RequestParam(value = "statusCode",required = false)Integer statusCode,
                               @PathVariable int current,@PathVariable int size){
        List<MonitorBaseVo> list = monitorBaseService.listByType(baseId, type, dovecoteNumber, statusCode, SecurityContextUtil.getUserDetails().getEnterpriseId());
        Page page = PageUtil.list2Page(list, current, size);
        List<MonitorBaseVo> records = page.getRecords();
        String accessToken = (String) redisTemplate.opsForValue().get("accessToken");
        for (MonitorBaseVo record : records) {
            record.setUrl("ezopen://open.ys7.com/"+record.getDeviceSerial()+"/"+record.getAisle()+".live&autoplay=0");
            record.setAccessToken(accessToken);
            record.setTemplete(2);
        }
        return Result.success().data(page);
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
    public Result findById(@PathVariable Long id){
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

//    @ApiOperation(value = "根据id更新Token")
//    @PostMapping(value = "/updateToken/{id}")
//    public Result updateToken(@PathVariable Long id){
//        return monitorBaseService.updateToken(id) ? Result.success(): Result.error();
//    }

    @ApiOperation(value = "更新摄像头信息")
    @PostMapping(value = "/update/{id}")
    public Result upData(@PathVariable("id") Long id,@RequestBody MonitorBaseDto monitorBaseDto){
        monitorBaseService.upData(id,monitorBaseDto);
        MonitorBase vs = monitorBaseService.getById(id);
        return Result.success().data(vs);
    }

}