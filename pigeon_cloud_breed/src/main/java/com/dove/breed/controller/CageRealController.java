package com.dove.breed.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.CageReal;
import com.dove.breed.entity.DovecoteOutBill;
import com.dove.breed.entity.vo.DovecoteOutBillVo;
import com.dove.breed.service.CageRealService;
import com.dove.entity.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-01
 */
@RestController
@RequestMapping("/breed/cage-real")
public class CageRealController {

    @Autowired
    private CageRealService cageRealService;

    @ApiOperation("获取实时鸽笼状态")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result list(@PathVariable("pageNum") Long pageNum, @PathVariable("pageSize") Long pageSize,
                       @Param("baseId") Long baseId, @Param("dovecoteNumber") String dovecoteNumber) {
        List<CageReal> allCage = cageRealService.getAllCage(baseId, dovecoteNumber);
        Page<CageReal> page = new Page<>(pageNum, pageSize);
        page.setRecords(allCage);
        page.setTotal(allCage.size());
        return Result.success("查询成功").data(page);
    }
}

