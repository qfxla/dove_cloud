package com.dove.authority.controller;

import com.dove.authority.service.EnterpriseService;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author run
 * @since 2021/4/14 20:21
 */
@RestController
@Api(tags = "溯源相关接口")
@CrossOrigin
@RequestMapping("/authority/trace")
public class TraceController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/enterprise/{id}/{uuid}")
    @ApiOperation("通过企业id查询企业信息（溯源模块调用）")
    public Result getEnterpriseById(@PathVariable(value = "id") Long enterpriseId
                                    , @PathVariable(value = "uuid") String uuid){
        return Result.success().data(enterpriseService.getEnterpriseById(enterpriseId, uuid));
    }
}
