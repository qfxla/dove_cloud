package com.dove.breed.controller;
import com.dove.breed.entity.dto.DovecoteEntryTypeDto;
import com.dove.breed.entity.vo.DovecoteEntryTypeVo;
import com.dove.breed.entity.vo.DovecoteVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.DovecoteEntryTypeService;
import com.dove.breed.entity.DovecoteEntryType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 鸽棚入仓类型表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/

@Slf4j
@Api(tags = "鸽棚入仓类型表")
@RestController
@RequestMapping("/breed/dovecoteEntryType")
public class DovecoteEntryTypeController {

    @Autowired
    public DovecoteEntryTypeService dovecoteEntryTypeService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DovecoteEntryTypeDto dovecoteEntryTypeDto){
        DovecoteEntryType dovecoteEntryType = convertUtil.convert(dovecoteEntryTypeDto, DovecoteEntryType.class);
        boolean save = dovecoteEntryTypeService.save(dovecoteEntryType);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = dovecoteEntryTypeService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody DovecoteEntryTypeDto dovecoteEntryTypeDto){
        DovecoteEntryType dovecoteEntryType = convertUtil.convert(dovecoteEntryTypeDto, DovecoteEntryType.class);
        List<DovecoteEntryType> dovecoteEntryTypeList = dovecoteEntryTypeService.list(new QueryWrapper<>(dovecoteEntryType));
        List<DovecoteEntryTypeVo> dovecoteEntryTypeVoList = convertUtil.convert(dovecoteEntryTypeList, DovecoteEntryTypeVo.class);
        return dovecoteEntryTypeVoList.size() > 0?Result.success("查询成功").data(dovecoteEntryTypeVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<DovecoteEntryType> page = dovecoteEntryTypeService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<DovecoteEntryTypeVo> page1 = convertUtil.convert(page, DovecoteEntryTypeVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        DovecoteEntryType dovecoteEntryType = dovecoteEntryTypeService.getById(id);
        DovecoteEntryTypeVo dovecoteEntryTypeVo = convertUtil.convert(dovecoteEntryType, DovecoteEntryTypeVo.class);
        return dovecoteEntryTypeVo != null? Result.success("查询成功").data(dovecoteEntryTypeVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody DovecoteEntryTypeDto dovecoteEntryTypeDto){
        DovecoteEntryType dovecoteEntryType = convertUtil.convert(dovecoteEntryTypeDto, DovecoteEntryType.class);
        dovecoteEntryType.setTypeId(id);
        boolean b = dovecoteEntryTypeService.updateById(dovecoteEntryType);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
