package com.dove.breed.controller;
import com.dove.breed.entity.dto.FeedStockDto;
import com.dove.breed.entity.vo.FeedStockVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.FeedStockService;
import com.dove.breed.entity.FeedStock;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 月底饲料盘点表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/

@Slf4j
@Api(tags = "月底饲料盘点表")
@RestController
@RequestMapping("/breed/feedStock")
public class FeedStockController {

    @Autowired
    public FeedStockService feedStockService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody FeedStockDto feedStockDto){
        FeedStock feedStock = new FeedStock();
        BeanUtils.copyProperties(feedStockDto,feedStock,FeedStock.class);
        boolean save = feedStockService.save(feedStock);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = feedStockService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody(required = false) FeedStockDto feedStockDto){
        FeedStock feedStock = new FeedStock();
        BeanUtils.copyProperties(feedStockDto,feedStock,FeedStock.class);
        List<FeedStock> feedStockList = feedStockService.list(new QueryWrapper<>(feedStock));
        List<FeedStockVo> feedStockVoList = convertUtil.convert(feedStockList, FeedStockVo.class);
        return feedStockList.size() > 0?Result.success("查询成功").data(feedStockVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<FeedStock> page = feedStockService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<FeedStockVo> page1 = convertUtil.convert(page,FeedStockVo.class);
        return page.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        FeedStock feedStock = feedStockService.getById(id);
        FeedStockVo feedStockVo = convertUtil.convert(feedStock,FeedStockVo.class);
        return feedStock == null? Result.success("查询成功").data(feedStockVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody FeedStockDto feedStockDto){
        FeedStock feedStock = new FeedStock();
        BeanUtils.copyProperties(feedStockDto,feedStock,FeedStock.class);
        feedStock.setId(id);
        boolean b = feedStockService.updateById(feedStock);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


    //TODO
    @ApiOperation(value = "获取鸽棚某月饲料使用量")
    @GetMapping("getFeedUsageOfMonth/{year}/{month}")
    public Result getFeedUsageOfMonth(@PathVariable("year")int year,@PathVariable("month")int month){
        return null;
    }

}
