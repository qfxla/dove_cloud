package com.dove.breed.controller;


import com.dove.breed.service.CagePositionService;
import com.dove.entity.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@RestController
@RequestMapping("/breed/cage-position")
public class CagePositionController {
    @Autowired
    private CagePositionService cagePositionService;

    @ApiOperation("查cageId")
    @PostMapping("/getCageId")
    public Result getCageId(@Param("baseId") Long baseId,
                            @Param("dovecoteNumber") String dovecoteNumber,
                            @Param("rowNo") int rowNo,
                            @Param("line") int line, @Param("columnNo") int columnNo){
        Long cageId = cagePositionService.getCageId(baseId, dovecoteNumber, rowNo, line, columnNo);
        return Result.success("查询成功").data(cageId);
    }
}

