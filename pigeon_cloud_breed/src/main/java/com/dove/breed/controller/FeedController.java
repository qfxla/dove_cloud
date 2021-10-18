package com.dove.breed.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.Feed;
import com.dove.breed.entity.dto.FeedDto;
import com.dove.breed.entity.vo.FeedVo;
import com.dove.breed.service.FeedService;
import com.dove.breed.utils.ConvertUtil;
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
 * 投喂信息表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-10-12
 */

@Slf4j
@Api(tags = "投喂信息表")
@RestController
@RequestMapping("/breed/feed")
public class FeedController {

    private ConvertUtil convertUtil = new ConvertUtil();

    @Autowired
    public FeedService feedService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody FeedDto feedDto){
        Feed convert = convertUtil.convert(feedDto, Feed.class);
        convert.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = feedService.save(convert);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = feedService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody Feed feed){
        List<Feed> feedList = feedService.list(new QueryWrapper<>(feed));
        return feedList.size() > 0?Result.success("查询成功").data(feedList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<Feed> page = feedService.page(
                new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0?Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id){
        Feed feed = feedService.getById(id);
        FeedVo feedVo = convertUtil.convert(feed, FeedVo.class);
        return feedVo == null? Result.success("查询成功").data(feedVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody FeedDto feedDto){
        Feed feed = convertUtil.convert(feedDto, Feed.class);
        feed.setId(id);
        boolean b = feedService.updateById(feed);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
