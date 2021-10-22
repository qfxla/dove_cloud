package com.dove.breed.controller.ui;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.service.CageRealService;
import com.dove.breed.utils.GetFileData;
import com.dove.breed.utils.PageUtil;
import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-11-15:43
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-鸽笼图")
@RestController
@RequestMapping("/ui/cage")
public class CageUiController {
    @Value("${BASE_UI_URL.cage}")
    public String baseUrl;
    @Autowired
    private CageRealService cageRealService;

    @ApiOperation("获取6个鸽笼详细信息")
    @GetMapping("doveDetails")
    public Result doveDetails() throws InterruptedException {
        //鸽笼信息
        List<CageRealVo> list = cageRealService.getAllCage(3L, "A01");
        Page page1 = PageUtil.list2Page(list, 1, 6);
        Page page2 = cageRealService.addAbnormal(page1);
        return page2.getTotal() > 0?Result.success("获取成功").data(page2) : Result.error(StatusCode.ERROR,"查询错误");
    }

    @ApiOperation("操作提醒列表")
    @GetMapping("remindList")
    public Result remindList(){
        String path = baseUrl + "操作列表.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }
}
