package com.dove.breed.controller;


import com.dove.breed.service.ManualIncubationService;
import com.dove.entity.Result;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 人工孵化表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-04
 */
@RestController
@RequestMapping("/breed/manual-incubation")
public class ManualIncubationController {
    @Autowired
    private ManualIncubationService manualIncubationService;

    @ApiModelProperty("添加孵化机数据")
    @PostMapping("/addManualIncubationData")
    public Result addManualIncubationData(@RequestParam("baseId")Long baseId,@RequestParam("dovecoteNumber")String dovecoteNumber,
                                          @RequestParam("one")int one,@RequestParam("two")int two,
                                          @RequestParam("three")int three,@RequestParam("four")int four){
        int i = manualIncubationService.addManualIncubationData(baseId, dovecoteNumber, one, two, three, four);
        return  i == 1?Result.success("添加成功") : Result.error("今日已填两次");
    }
}

