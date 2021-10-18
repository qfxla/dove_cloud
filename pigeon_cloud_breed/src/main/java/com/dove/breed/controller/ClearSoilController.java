package com.dove.breed.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.ClearSoil;
import com.dove.breed.entity.dto.ClearSoilDto;
import com.dove.breed.entity.vo.ClearSoilVo;
import com.dove.breed.service.ClearSoilService;
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
 * 清粪信息表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-10-12
 */

@Slf4j
@Api(tags = "清粪信息表")
@RestController
@RequestMapping("/breed/clear-soil")
public class ClearSoilController {

    private ConvertUtil convertUtil = new ConvertUtil();

    @Autowired
    public ClearSoilService clearSoilService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ClearSoilDto clearSoilDto){
        ClearSoil convert = convertUtil.convert(clearSoilDto, ClearSoil.class);
        convert.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = clearSoilService.save(convert);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = clearSoilService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody ClearSoil clearSoil){
        List<ClearSoil> clearSoilList = clearSoilService.list(new QueryWrapper<>(clearSoil));
        return clearSoilList.size() > 0?Result.success("查询成功").data(clearSoilList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<ClearSoil> page = clearSoilService.page(
                new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0?Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id){
        ClearSoil clearSoil = clearSoilService.getById(id);
        ClearSoilVo clearSoilVo = convertUtil.convert(clearSoil, ClearSoilVo.class);
        return clearSoilVo == null? Result.success("查询成功").data(clearSoilVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody ClearSoilDto clearSoilDto){
        ClearSoil clearSoil = convertUtil.convert(clearSoilDto, ClearSoil.class);
        clearSoil.setId(id);
        boolean b = clearSoilService.updateById(clearSoil);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}