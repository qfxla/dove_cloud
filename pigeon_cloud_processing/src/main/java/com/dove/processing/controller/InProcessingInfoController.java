package com.dove.processing.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.processing.entity.Dto.InProcessingInfoDto;
import com.dove.processing.entity.InProcessingInfo;
import com.dove.processing.entity.Vo.InProcessingBothVo;
import com.dove.processing.entity.Vo.InProcessingInfoBindBillVo;
import com.dove.processing.service.InProcessingBillService;
import com.dove.processing.service.InProcessingInfoService;
import com.dove.processing.utils.ConvertUtil;
import com.dove.entity.Result;
import com.sun.xml.bind.v2.model.core.ID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
    * 加工厂入库信息表 前端控制器
    * </p>
*
* @author WTL
* @since 2021-08-23
*/

@Slf4j
@Api(tags = "加工厂入库信息表")
@CrossOrigin
@RestController
@RequestMapping("/processing/inProcessingInfo")
public class InProcessingInfoController {

    @Resource
    private InProcessingInfoService inProcessingInfoService;

    @Resource
    private InProcessingBillService inProcessingBillService;

    @Resource
    private ConvertUtil convertUtil;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@RequestBody InProcessingInfoDto inProcessingInfoDto){
        boolean isSuccess = inProcessingInfoService.saveBothInProcessingInfo(inProcessingInfoDto);
        return isSuccess ? Result.success("添加成功") : Result.error("添加失败");
    }

    @ApiOperation(value = "根据表in_id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        Map<String,Object> map = new HashMap<>();
        map.put("in_id", id);
        boolean deleteById = inProcessingInfoService.removeByMap(map) && inProcessingBillService.removeByMap(map);
        return deleteById ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "批量删除（根据in_id）")
    @DeleteMapping("/deletion/batch")
    public Result deleteProcessBatchById(@ApiParam("id数组") @RequestParam("ids") ArrayList<Long> ids) {
        boolean deleteBatchByIds = inProcessingBillService.removeByIds(ids) && inProcessingInfoService.deleteByIds(ids);
        return deleteBatchByIds ? Result.success("删除成功") : Result.error("删除失败");
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object list(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        Page<InProcessingInfoBindBillVo> page = inProcessingInfoService.getInProcessingByPage(pageNum,pageSize);
        return page.getTotal() > 0 ? Result.success("分页成功").data(page) : Result.error();
    }

    @ApiOperation(value = "根据in_id查询加工厂入库详情")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") long id){
        List<InProcessingInfoBindBillVo> inProcessingInfo = inProcessingInfoService.getInInfoByInId(id);
        return inProcessingInfo.size() > 0 ? Result.success("查询详情成功").data(inProcessingInfo) : Result.error("查询失败");
    }

    @ApiOperation(value = "根据in_id修改")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable("id") long id, @RequestBody InProcessingInfoDto inProcessingInfoDto){
        boolean updateInfo = inProcessingInfoService.updateBindInfo(id,inProcessingInfoDto);
        return updateInfo ? Result.success("修改成功") : Result.error("修改失败");
    }

    @ApiOperation(value = "模糊查询获取入库信息表数据（分页）",notes = "分页 根据入库仓库 原料名称")
    @GetMapping("/like/{value}/{no}/{size}")
    public Result getFactorysByLikeSearch(@ApiParam("入库仓库或者原料名称") @PathVariable("value") String value,
                                          @ApiParam("第几页") @PathVariable("no") int no,
                                          @ApiParam("每页规格") @PathVariable("size") int size) {
    Page<InProcessingInfoBindBillVo> page = inProcessingInfoService.getInProcessingByLikeSearch(value,no,size);
    return page.getTotal() > 0 ? Result.success("模糊查询成功").data(page) : Result.error("模糊查找失败");
    }

    @ApiOperation(value = "根据一个时间段查询东西（分页）")
    @GetMapping("/get/{no}/{size}")
    public Result getInfoByTiemStamp(@ApiParam("第几页") @PathVariable("no") int no,
                                     @ApiParam("每页规格") @PathVariable("size") int size,
                                     @ApiParam("开始时间") @RequestParam("firstTime") String firstTime,
                                     @ApiParam("结束时间") @RequestParam("lastTime") String lastTime) {
        Page<InProcessingInfoBindBillVo> page = inProcessingInfoService.getDataByDateTime(no,size,firstTime,lastTime);
        return page.getTotal() > 0 ? Result.success("查询成功").data(page) : Result.error("数据为空");
    }

}
