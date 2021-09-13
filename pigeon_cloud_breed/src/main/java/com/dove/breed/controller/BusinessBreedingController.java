package com.dove.breed.controller;



import com.dove.breed.entity.dto.BusinessBreedingDto;
import com.dove.breed.entity.vo.BusinessBreedingVo;
import com.dove.breed.service.BusinessBreedingService;
import com.dove.breed.entity.BusinessBreeding;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.utils.ConvertUtil;
import com.dove.entity.Result;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    import org.springframework.web.bind.annotation.RestController;

/**
* <p>
    * 商家表 前端控制器
    * </p>
*
* @author zcj
* @since 2021-08-18
*/
@CrossOrigin
@Slf4j
@Api(tags = "商家表")
@RestController
@RequestMapping("/breed/businessBreeding")
public class BusinessBreedingController {

    @Autowired
    public BusinessBreedingService businessBreedingService;

    @Autowired
    public ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody BusinessBreedingDto businessBreedingDto){
        BusinessBreeding businessBreeding = convertUtil.convert(businessBreedingDto, BusinessBreeding.class);
        businessBreeding.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = businessBreedingService.save(businessBreeding);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = businessBreedingService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody BusinessBreedingDto businessBreedingDto){
        BusinessBreeding businessBreeding = convertUtil.convert(businessBreedingDto, BusinessBreeding.class);
        List<BusinessBreeding> businessBreedingList = businessBreedingService.list(new QueryWrapper<>(businessBreeding));
        List<BusinessBreedingVo> businessBreedingVoList = convertUtil.convert(businessBreedingList, BusinessBreedingVo.class);
        return businessBreedingVoList.size() > 0?Result.success("查询成功").data(businessBreedingVoList) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<BusinessBreeding> page = businessBreedingService.page(
        new Page<>(pageNum, pageSize), null);
        IPage<BusinessBreedingVo> page1 = convertUtil.convert(page, BusinessBreedingVo.class);
        return page1.getTotal() > 0?Result.success("分页成功").data(page1) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        BusinessBreeding businessBreeding = businessBreedingService.getById(id);
        BusinessBreedingVo businessBreedingVo = convertUtil.convert(businessBreeding, BusinessBreedingVo.class);
        return businessBreedingVo != null? Result.success("查询成功").data(businessBreedingVo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody BusinessBreedingDto businessBreedingDto){
        BusinessBreeding businessBreeding = convertUtil.convert(businessBreedingDto, BusinessBreeding.class);
        businessBreeding.setId(id);
        boolean b = businessBreedingService.updateById(businessBreeding);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }


}
