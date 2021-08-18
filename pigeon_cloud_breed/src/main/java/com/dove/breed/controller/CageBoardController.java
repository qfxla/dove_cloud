package com.dove.breed.controller;



import com.dove.breed.service.CageBoardService;
import com.dove.breed.entity.CageBoard;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 板子鸽笼关联表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/

@Slf4j
@Api(tags = "板子鸽笼关联表")
@RestController
@RequestMapping("/breed/cageBoard")
public class CageBoardController {

    @Autowired
    public CageBoardService cageBoardService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody CageBoard cageBoard){
        boolean save = cageBoardService.save(cageBoard);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = cageBoardService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody CageBoard cageBoard){
        List<CageBoard> cageBoardList = cageBoardService.list(new QueryWrapper<>(cageBoard));
        return cageBoardList.size() > 0?Result.success("查询成功").data(cageBoardList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<CageBoard> page = cageBoardService.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0?Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        CageBoard cageBoard = cageBoardService.getById(id);
        return cageBoard == null? Result.success("查询成功").data(cageBoard) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") String id, @RequestBody CageBoard cageBoard){
        cageBoard.setCageId(id);
        boolean b = cageBoardService.updateById(cageBoard);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
