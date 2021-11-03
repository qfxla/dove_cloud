package com.dove.breed.controller;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.dove.breed.entity.dto.OperatorDto;
import com.dove.breed.entity.vo.FeedMachineVo;
import com.dove.breed.entity.vo.OperatorVo;
import com.dove.breed.service.OperatorService;
import com.dove.breed.entity.Operator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import com.dove.util.SecurityContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-11-03
 */

@CrossOrigin
@Slf4j
@Api(value = "操作人列表", tags = "操作人列表")
@RestController
@RequestMapping("/breed/operator")
public class OperatorController {

    private ConvertUtil convertUtil = new ConvertUtil();

    @Autowired
    public OperatorService operatorService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody OperatorDto operatorDto){
        if(isMobile(operatorDto.getPhone()).getStatusCode() == StatusCode.ERROR.getCode()){
            return Result.error("手机号码可能存在，或者格式错误");
        }
        Operator convert = convertUtil.convert(operatorDto, Operator.class);
        convert.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        boolean save = operatorService.save(convert);
        return save? Result.success("保存成功") : Result.error("保存失败");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = operatorService.removeById(id);
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/deleteList")
    public Result deleteList(@RequestParam("idList") List idList){
        boolean b = operatorService.removeByIds(idList);
        return b ? Result.success("批量删除成功") : Result.error("批量删除失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{current}/{size}")
    public Object list(@RequestParam(value = "baseId", required = false) Long baseId,
                       @RequestParam(value = "dovecoteNumber", required = false) String dovecoteNumber,
                       @RequestParam(value = "name", required = false) String name,
                       @PathVariable("current")Integer current, @PathVariable("size")Integer size){
        List<OperatorVo> list = operatorService.listByType(baseId, dovecoteNumber, name, SecurityContextUtil.getUserDetails().getEnterpriseId());
        Page page = PageUtil.list2Page(list, current, size);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error("分页失败");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") String id){
        Operator operator = operatorService.getById(id);
        OperatorVo convert = convertUtil.convert(operator, OperatorVo.class);
        return convert != null? Result.success("查询成功").data(convert) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody OperatorDto operatorDto){
        if(isMobile(operatorDto.getPhone()).getStatusCode() == StatusCode.ERROR.getCode()){
            return Result.error("修改失败");
        }
        Operator convert = convertUtil.convert(operatorDto, Operator.class);
        convert.setId(id);
        boolean b = operatorService.updateById(convert);
        return b?Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "查找相应基地和鸽棚的负责人列表")
    @GetMapping("/comboBox")
    public Result comboBox(@RequestParam(value = "baseId") Long baseId,
                           @RequestParam(value = "dovecoteNumber") String dovecoteNumber){
        List<String> list = operatorService.comboBox(baseId, dovecoteNumber, SecurityContextUtil.getUserDetails().getEnterpriseId());
        return list != null ? Result.success("分页成功").data(list) : Result.error("分页失败");
    }

    @ApiOperation(value = "校验手机号码是否可用")
    @GetMapping("/isMobile/{phone}")
    public Result isMobile(@PathVariable(value = "phone") Long phone) {
        if(!Validator.isMobile(phone.toString())){
            return Result.error("手机号码格式不正确");
        }
        QueryWrapper<Operator> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        if(Validator.isNotNull(operatorService.getOne(wrapper))){
            return Result.error("手机号码已经被使用了");
        }
        return Result.success("手机号码可用");
    }
}
