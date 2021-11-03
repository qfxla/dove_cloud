package com.dove.breed.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.Drinking;
import com.dove.breed.entity.FeedHistory;
import com.dove.breed.entity.FeedMachine;
import com.dove.breed.entity.dto.DrinkingDto;
import com.dove.breed.entity.dto.FeedHistoryDto;
import com.dove.breed.entity.dto.FeedMachineDto;
import com.dove.breed.entity.vo.FeedHistoryVo;
import com.dove.breed.service.FeedHistoryService;
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
 * @author zcj
 * @creat 2021-10-22-11:13
 */
@CrossOrigin
@Slf4j
@Api(tags = "投喂历史表")
@RestController
@RequestMapping("/breed/feedHistory")
public class FeedHistoryController {

    private ConvertUtil convertUtil = new ConvertUtil();

    @Autowired
    public FeedHistoryService feedHistoryService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody FeedHistoryDto feedHistoryDto){
        FeedHistory convert = convertUtil.convert(feedHistoryDto, FeedHistory.class);
        convert.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = feedHistoryService.save(convert);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = feedHistoryService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/deleteList")
    public Result delete(@RequestParam("idList") List idList){
        boolean b = feedHistoryService.removeByIds(idList);
        return b ? Result.success("批量删除成功") : Result.error("批量删除失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{current}/{size}")
    public Object list(@RequestParam(value = "baseId", required = false) Long baseId,
                       @RequestParam(value = "dovecoteNumber", required = false) String dovecoteNumber,
                       @RequestParam(value = "feedNumber", required = false) String feedNumber,
                       @RequestParam(value = "operator", required = false) String operator,
                       @RequestParam(value = "startTime",required = false) String startTime,
                       @RequestParam(value = "endTime",required = false)String endTime,
                       @PathVariable(value = "current")Integer current, @PathVariable("size")Integer size){
        List<FeedHistoryVo> list = feedHistoryService.listByType(baseId, dovecoteNumber, feedNumber, operator, startTime, endTime, SecurityContextUtil.getUserDetails().getEnterpriseId());
        Page page = PageUtil.list2Page(list, current, size);
        return page.getTotal() > 0? Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id){
        FeedHistory feedHistory = feedHistoryService.getById(id);
        FeedHistoryVo feedHistoryVo = convertUtil.convert(feedHistory, FeedHistoryVo.class);
        return feedHistoryVo != null? Result.success("查询成功").data(feedHistoryVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody FeedHistoryDto feedHistoryDto){
        FeedHistory convert = convertUtil.convert(feedHistoryDto, FeedHistory.class);
        convert.setId(id);
        boolean b = feedHistoryService.updateById(convert);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }
}