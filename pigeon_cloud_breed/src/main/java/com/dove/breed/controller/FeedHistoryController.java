package com.dove.breed.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.FeedHistory;
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
@Slf4j
@Api(tags = "投喂历史表")
@RestController
@RequestMapping("/breed/feedHistory")
public class FeedHistoryController {

    private ConvertUtil convertUtil = new ConvertUtil();

    @Autowired
    public FeedHistoryService feedHistoryService;

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = feedHistoryService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
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

    @ApiOperation(value = "获取所有操作人")
    @GetMapping("/getAllOperator")
    public Result getAllOperator(){
        List<String> operatorList = feedHistoryService.getAllOperator();
        return operatorList != null? Result.success("查询成功").data(operatorList) : Result.error("查询失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id){
        FeedHistory feedHistory = feedHistoryService.getById(id);
        FeedHistoryVo feedHistoryVo = convertUtil.convert(feedHistory, FeedHistoryVo.class);
        return feedHistoryVo != null? Result.success("查询成功").data(feedHistoryVo) : Result.error("查询失败");
    }
}