package com.dove.breed.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.ManualIncubation;
import com.dove.breed.entity.vo.ManualIncubationVo;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.service.ManualIncubationService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p>
 * 人工孵化表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-04
 */
@Api(tags = "孵化机")
@RestController
@RequestMapping("/breed/manual-incubation")
public class ManualIncubationController {
    @Autowired
    private ManualIncubationService manualIncubationService;
    @Autowired
    private DovecoteMapper dovecoteMapper;
    @Autowired
    private ConvertUtil convertUtil;

    @ApiModelProperty("添加孵化机数据")
    @PostMapping("/addManualIncubationData")
    public Result addManualIncubationData(@RequestParam("baseId")Long baseId,@RequestParam("dovecoteNumber")String dovecoteNumber,
                                          @RequestParam("one")int one,@RequestParam("two")int two,
                                          @RequestParam("three")int three,@RequestParam("four")int four){
        int i = manualIncubationService.addManualIncubationData(baseId, dovecoteNumber, one, two, three, four);
        return  i == 1?Result.success("添加成功") : Result.error("今日已填两次");
    }

    @ApiOperation("查看孵化机记录")
    @GetMapping("/getByDovecoteNumber")
    public Result getByDovecoteNumber(Long baseId, String dovecoteNumber,int pageNum,int pageSize){
        Page<ManualIncubationVo> page = manualIncubationService.getByDovecoteNumber(baseId, dovecoteNumber, pageNum, pageSize);
        return Result.success("查询成功").data(page);
    }

    @ApiOperation("更改孵化记录")
    @GetMapping("/updateData")
    public Result updateData(@RequestBody ManualIncubation manualIncubation){
        boolean b = manualIncubationService.updateById(manualIncubation);
        return b ?Result.success("更改成功"):Result.error("更改失败");
    }

    @ApiOperation("重置某条孵化记录")
    @GetMapping("/toZero")
    public Result toZero(@RequestParam("id")Long id){
        ManualIncubation manualIncubation = manualIncubationService.getById(id);
        manualIncubation.setAmNumber(0);
        manualIncubation.setPmNumber(0);
        boolean b = manualIncubationService.updateById(manualIncubation);
        return b?Result.success("重置成功") : Result.error("重置失败");
    }

    @ApiOperation("通过日期获取")
    @GetMapping("/getByDate")
    public Result getByDate(@RequestParam("baseId")Long baseId,@RequestParam("year")int year,
                            @RequestParam("month")int month,@RequestParam("day")int day,
                            @RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        List<ManualIncubationVo> list = manualIncubationService.getByDate(baseId, year, month, day);
        Page<ManualIncubationVo> page = PageUtil.myPage(list, pageNum, pageSize);
        return page != null?Result.success("获取成功").data(page) : Result.error("获取失败");
    }

    @ApiOperation("通过蛋类型获取")
    @GetMapping("/getByType")
    public Result getByType(@RequestParam("baseId")Long baseId,@RequestParam("type") int type,
        @RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
            QueryWrapper<ManualIncubation> wrapper = new QueryWrapper<>();
            wrapper.eq("type",type).
                    eq("base_id",baseId);
        List<ManualIncubation> list = manualIncubationService.list(wrapper);
        List<ManualIncubationVo> list1 = convertUtil.convert(list, ManualIncubationVo.class);
        Page<ManualIncubationVo> page = PageUtil.myPage(list1, pageNum, pageSize);
        return Result.success("获取成功").data(page);
    }
}

