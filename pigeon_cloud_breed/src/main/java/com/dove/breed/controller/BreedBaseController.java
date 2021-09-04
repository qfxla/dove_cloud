package com.dove.breed.controller;



import com.dove.breed.entity.dto.BreedBaseDto;
import com.dove.breed.entity.vo.BreedBaseVo;
import com.dove.breed.service.BreedBaseService;
import com.dove.breed.entity.BreedBase;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.utils.ConvertUtil;
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
    * 养殖基地信息表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "养殖基地信息表")
@RestController
@RequestMapping("/breed/Base")
public class BreedBaseController {

    @Autowired
    public BreedBaseService breedBaseService;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody BreedBaseDto breedBaseDto){
        BreedBase breedBase = convertUtil.convert(breedBaseDto, BreedBase.class);
        boolean save = breedBaseService.save(breedBase);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = breedBaseService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody BreedBaseDto breedBaseDto){
        BreedBase breedBase = convertUtil.convert(breedBaseDto, BreedBase.class);
        List<BreedBase> breedBaseList = breedBaseService.list(new QueryWrapper<>(breedBase));
        List<BreedBaseVo> breedBasesVoList = convertUtil.convert(breedBaseList,BreedBaseVo.class);
        return breedBaseList.size() > 0?Result.success("查询成功").data(breedBasesVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<BreedBase> page = breedBaseService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<BreedBaseVo> page1 = convertUtil.convert(page, BreedBaseVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        BreedBase breedBase = breedBaseService.getById(id);
        BreedBaseVo breedBaseVo = convertUtil.convert(breedBase, BreedBaseVo.class);
        return breedBaseVo != null? Result.success("查询成功").data(breedBaseVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody BreedBaseDto breedBaseDto){
        BreedBase breedBase = convertUtil.convert(breedBaseDto, BreedBase.class);
        breedBase.setId(id);
        boolean b = breedBaseService.updateById(breedBase);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
