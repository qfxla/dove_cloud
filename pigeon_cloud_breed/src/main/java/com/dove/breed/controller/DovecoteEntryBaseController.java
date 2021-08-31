package com.dove.breed.controller;
import com.dove.breed.entity.dto.DovecoteEntryBaseDto;
import com.dove.breed.entity.vo.DovecoteEntryBaseVo;
import com.dove.breed.entity.vo.DovecoteVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.DovecoteEntryBaseService;
import com.dove.breed.entity.DovecoteEntryBase;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 鸽棚入仓信息表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/

@Slf4j
@Api(tags = "鸽棚入仓信息表")
@RestController
@RequestMapping("/breed/dovecoteEntryBase")
public class DovecoteEntryBaseController {

    @Autowired
    public DovecoteEntryBaseService dovecoteEntryBaseService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DovecoteEntryBaseDto dovecoteEntryBaseDto){
        DovecoteEntryBase dovecoteEntryBase = convertUtil.convert(dovecoteEntryBaseDto, DovecoteEntryBase.class);
        boolean save = dovecoteEntryBaseService.save(dovecoteEntryBase);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = dovecoteEntryBaseService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody DovecoteEntryBaseDto dovecoteEntryBaseDto){
        DovecoteEntryBase dovecoteEntryBase = convertUtil.convert(dovecoteEntryBaseDto, DovecoteEntryBase.class);
        List<DovecoteEntryBase> dovecoteEntryBaseList = dovecoteEntryBaseService.list(new QueryWrapper<>(dovecoteEntryBase));
        List<DovecoteEntryBaseDto> dovecoteEntryBaseDtoList = convertUtil.convert(dovecoteEntryBaseList, DovecoteEntryBaseDto.class);
        return dovecoteEntryBaseList.size() > 0?Result.success("查询成功").data(dovecoteEntryBaseDtoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<DovecoteEntryBase> page = dovecoteEntryBaseService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<DovecoteEntryBaseVo> page1 = convertUtil.convert(page, DovecoteEntryBaseVo.class);
        return page.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        DovecoteEntryBase dovecoteEntryBase = dovecoteEntryBaseService.getById(id);
        DovecoteVo dovecoteVo = convertUtil.convert(dovecoteEntryBase, DovecoteVo.class);
        return dovecoteVo != null? Result.success("查询成功").data(dovecoteVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody DovecoteEntryBaseDto dovecoteEntryBaseDto){
        DovecoteEntryBase dovecoteEntryBase = convertUtil.convert(dovecoteEntryBaseDto, DovecoteEntryBase.class);
        dovecoteEntryBase.setId(id);
        boolean b = dovecoteEntryBaseService.updateById(dovecoteEntryBase);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
