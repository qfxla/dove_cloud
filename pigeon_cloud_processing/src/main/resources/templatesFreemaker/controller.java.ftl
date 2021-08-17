package ${package.Controller};



import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pigeon.entity.Result;
import com.pigeon.entity.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/

@Slf4j
@Api(tags = "${table.comment!}")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {

    @Autowired
    private ${table.serviceName} ${table.entityPath}Service;

    @Autowired
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody ${entity}Dto ${table.entityPath}Dto){
        ${entity} ${table.entityPath} = convertUtil.convert(${table.entityPath}Dto, ${entity}.class);
        boolean addInfo = ${table.entityPath}Service.save(${table.entityPath});
        return addInfo ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表id删除")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        boolean deleteByid = ${table.entityPath}Service.removeById(id);
        return deleteByid ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public Result list(@RequestBody ${entity}Dto ${table.entityPath}Dto){
        ${entity} ${table.entityPath} = convertUtil.convert(${table.entityPath}Dto, ${entity}.class);
        List<${entity}> ${table.entityPath}List = ${table.entityPath}Service.list(new QueryWrapper<>(${table.entityPath}));
        return ${table.entityPath}List.size() > 0 ? Result.success("查询成功").data(${table.entityPath}List) : Result.error("查询失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<${entity}> page = ${table.entityPath}Service.page(
        new Page<>(pageNum, pageSize), null);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(id);
        return ${table.entityPath} != null ? Result.success("查询详情成功").data(${table.entityPath}) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody ${entity}Dto ${table.entityPath}Dto){
        ${entity} ${table.entityPath} = convertUtil.convert(${table.entityPath}Dto, ${entity}.class);
        ${table.entityPath}.setId(id);
        boolean updateInfo = ${table.entityPath}Service.updateById(${table.entityPath});
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

    </#if>

}
</#if>
