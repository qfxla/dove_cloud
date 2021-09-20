package com.dove.breed.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.ManualIncubation;
import com.dove.breed.entity.dto.ManualIncubationDto;
import com.dove.breed.entity.vo.ManualIncubationVo;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.mapper.ManualIncubationMapper;
import com.dove.breed.service.DovecoteService;
import com.dove.breed.service.ManualIncubationService;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.channels.WritePendingException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 孵化机 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-09
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
    @Autowired
    private DovecoteService dovecoteService;
    @Autowired
    private ManualIncubationMapper manualIncubationMapper;

    @ApiModelProperty("添加孵化机数据")
    @PostMapping("/addManualIncubationData")
    public Result addManualIncubationData(@RequestBody ManualIncubationDto manualIncubationDto){
        int i = manualIncubationService.addManualIncubationData(manualIncubationDto);
        return  i == 1?Result.success("添加成功") : Result.error("当天已填两次");
    }

    @ApiOperation("查看孵化机记录")
    @GetMapping("/getByDovecoteNumber")
    public Result getByDovecoteNumber(Long baseId, String dovecoteNumber,int pageNum,int pageSize){
        List<ManualIncubation> list = manualIncubationService.getByDovecoteNumber(baseId, dovecoteNumber, pageNum, pageSize);
        List<ManualIncubationVo> list1 = convertUtil.convert(list, ManualIncubationVo.class);
        list1 = list1.stream().sorted(Comparator.comparing(ManualIncubationVo::getLaborTime).reversed()).collect(Collectors.toList());
        Page page = PageUtil.list2Page(list1, pageNum, pageSize);
        return Result.success("查询成功").data(page);
    }

    @ApiOperation("更改孵化记录")
    @PostMapping("/updateData")
    public Result updateData(@RequestParam("id")Long id,@RequestBody ManualIncubationDto manualIncubationDto){
        ManualIncubation manualIncubation = convertUtil.convert(manualIncubationDto, ManualIncubation.class);
        manualIncubation.setId(id);
        QueryWrapper<ManualIncubation> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        List<ManualIncubation> list = manualIncubationService.list(wrapper);
        if (list.size() == 0){
            return Result.error("无该id");
        }
        manualIncubation.setBreeder(list.get(0).getBreeder());
        boolean b = manualIncubationService.updateById(manualIncubation);
        return b ?Result.success("更改成功"):Result.error("更改失败");
    }

    @ApiOperation("删除某条孵化记录")
    @GetMapping("/deleteById")
    public Result toZero(@RequestParam("id")Long id){
        boolean b = manualIncubationService.removeById(id);
        return b?Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation("通过日期获取")
    @GetMapping("/getByDate")
    public Result getByDate(@RequestParam("baseId")Long baseId,@RequestParam("year")int year,
                            @RequestParam("month")int month,@RequestParam("day")int day,
                            @RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        List<ManualIncubationVo> list = manualIncubationService.getByDate(baseId, year, month, day);
        Page<ManualIncubationVo> page = PageUtil.list2Page(list, pageNum, pageSize);
        return page != null?Result.success("获取成功").data(page) : Result.error("获取失败");
    }

    @ApiOperation("删除")
    @DeleteMapping("/deleteDataById")
    public Result deleteDataById(@RequestParam("id")Long id){
        boolean b = manualIncubationService.removeById(id);
        return b?Result.success("删除成功"):Result.error("删除失败");
    }

    //    @ApiOperation("导出鸽棚日结数据")
    @ApiOperation(value = "导出清单", notes = "export", produces = "application/octet-stream")
    @GetMapping("exportData")
    public void exportDailyData(HttpServletResponse response, @RequestParam("baseId") Long baseId){
        manualIncubationService.exportDailyData(response,baseId);
    }

    @ApiOperation(value = "获取孵化机7天总数据")
    @GetMapping("/get7DayOfOneIncubation")
    public Result get7DayOfOneIncubation(@RequestParam("baseId")Long baseId,@RequestParam("dovecoteNumber")String dovecoteNumber){
        ManualIncubationVo manualIncubationVo = manualIncubationService.get7DayOfOneIncubation(baseId, dovecoteNumber);
        return manualIncubationVo.getBaseId() != 0L?Result.success("获取成功").data(manualIncubationVo) : Result.error("获取失败");
    }

    @ApiOperation(value = "获取孵化机7天各天数据")
    @GetMapping("/getSevenDay")
    public Result getSevenDay(@RequestParam("baseId")Long baseId,@RequestParam("dovecoteNumber")String dovecoteNumber){
        List<ManualIncubation> list = manualIncubationService.getSevenDay(baseId, dovecoteNumber);
        List<ManualIncubationVo> voList = convertUtil.convert(list, ManualIncubationVo.class);
        return voList.size()>0 ? Result.success("获取成功").data(voList) : Result.error("获取失败");
    }

}

