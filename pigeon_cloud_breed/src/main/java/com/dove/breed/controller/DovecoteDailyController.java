package com.dove.breed.controller;


import com.dove.breed.entity.CageReal;
import com.dove.breed.entity.DovecoteDaily;
import com.dove.breed.entity.vo.DovecoteDailyVo;
import com.dove.breed.service.DovecoteDailyService;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.Pipeline;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 鸽棚日结表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-02
 */
@CrossOrigin
@Api(tags = "鸽棚日结表")
@RestController
@RequestMapping("/breed/dovecote-daily")
public class DovecoteDailyController {

    @Autowired
    private DovecoteDailyService dovecoteDailyService;

    @ApiOperation(value = "获取日结表数据")
    @GetMapping("/getDovecoteDaily")
    public Result getDovecoteDaily(@RequestParam("baseId")Long baseId, @RequestParam("dovecoteNumber")String dovecoteNumber,
                                   @RequestParam("year")Integer year, @RequestParam("month")Integer month, @RequestParam("day")Integer day){
        DovecoteDailyVo dovecoteDaily = dovecoteDailyService.getDovecoteDaily(baseId, dovecoteNumber, year, month, day);
        return dovecoteDaily != null? Result.success("获取成功").data(dovecoteDaily) : Result.error("获取失败");
    }

    @ApiOperation(value = "根据基地id获取所有鸽棚日结")
    @GetMapping("/getAllDovecoteDaily")
    public Result getAllDovecoteDaily(@RequestParam("baseId")Long baseId,@RequestParam("year")int year,
                                      @RequestParam("month")int month,@RequestParam("day")int day){

        List<DovecoteDailyVo> allDovecoteDaily = dovecoteDailyService.getAllDovecoteDaily(baseId, year, month, day);
        return Result.success("查询成功").data(allDovecoteDaily);
    }

    @ApiOperation(value = "根据基地id获取所有鸽棚日结（分页）")
    @GetMapping("/getAllDovecoteDailyByPage")
    public Result getAllDovecoteDailyByPage(@RequestParam("baseId")Long baseId,@RequestParam("year")int year,
                                      @RequestParam("month")int month,@RequestParam("day")int day,
                                            @RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        List<DovecoteDailyVo> allDovecoteDaily = dovecoteDailyService.getAllDovecoteDaily(baseId, year, month, day);

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DovecoteDailyVo> pageFromList = PageUtil.createPageFromList(allDovecoteDaily, pageable);
        return Result.success("查询成功").data(pageFromList);
    }


//    @ApiOperation("导出鸽棚日结数据")
    @ApiOperation(value = "导出清单", notes = "export", produces = "application/octet-stream")
    @GetMapping("exportDailyData")
    public void exportDailyData(HttpServletResponse response,@RequestParam("baseId") Long baseId){
        dovecoteDailyService.exportDailyData(response,baseId);
    }

}

