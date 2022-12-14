package com.dove.breed.controller;
import com.dove.breed.entity.dto.DovecoteOutBaseDto;
import com.dove.breed.entity.vo.DovecoteOutBaseVo;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;


import com.dove.breed.service.DovecoteOutBaseService;
import com.dove.breed.entity.DovecoteOutBase;
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
    * 鸽棚出仓信息表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "鸽棚出仓信息表")
@RestController
@RequestMapping("/breed/dovecoteOutBase")
public class DovecoteOutBaseController {

    @Autowired
    public DovecoteOutBaseService dovecoteOutBaseService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody DovecoteOutBaseDto dovecoteOutBaseDto){
        DovecoteOutBase dovecoteOutBase = convertUtil.convert(dovecoteOutBaseDto, DovecoteOutBase.class);
        boolean save = dovecoteOutBaseService.save(dovecoteOutBase);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = dovecoteOutBaseService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody DovecoteOutBaseDto dovecoteOutBaseDto){
        DovecoteOutBase dovecoteOutBase = convertUtil.convert(dovecoteOutBaseDto, DovecoteOutBase.class);
        List<DovecoteOutBase> dovecoteOutBaseList = dovecoteOutBaseService.list(new QueryWrapper<>(dovecoteOutBase));
        List<DovecoteOutBaseVo> dovecoteOutBaseVoList = convertUtil.convert(dovecoteOutBaseList, DovecoteOutBaseVo.class);
        return dovecoteOutBaseVoList.size() > 0?Result.success("查询成功").data(dovecoteOutBaseVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<DovecoteOutBase> page = dovecoteOutBaseService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<DovecoteOutBaseVo> page1 = convertUtil.convert(page, DovecoteOutBaseVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        DovecoteOutBase dovecoteOutBase = dovecoteOutBaseService.getById(id);
        DovecoteOutBaseVo dovecoteOutBaseVo = convertUtil.convert(dovecoteOutBase, DovecoteOutBaseVo.class);
        return dovecoteOutBaseVo != null? Result.success("查询成功").data(dovecoteOutBaseVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody DovecoteOutBaseDto dovecoteOutBaseDto){
        DovecoteOutBase dovecoteOutBase = convertUtil.convert(dovecoteOutBaseDto, DovecoteOutBase.class);
        dovecoteOutBase.setId(id);
        boolean b = dovecoteOutBaseService.updateById(dovecoteOutBase);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
