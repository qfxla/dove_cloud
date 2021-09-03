package com.dove.breed.controller;


import com.dove.breed.service.CagePositionService;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@CrossOrigin
@Api(tags = "鸽棚位置表")
@RestController
@RequestMapping("/breed/cage-position")
public class CagePositionController {
    @Autowired
    private CagePositionService cagePositionService;

    @ApiOperation("根据row，line，column查cageId")
    @PostMapping("/getCageId")
    public Result getCageId(@RequestParam("baseId") Long baseId,
                            @RequestParam("dovecoteNumber") String dovecoteNumber,
                            @RequestParam("rowNo") int rowNo,
                            @RequestParam("line") int line, @RequestParam("columnNo") int columnNo){
        Long cageId = cagePositionService.getCageId(baseId, dovecoteNumber, rowNo, line, columnNo);
        return Result.success("查询成功").data(cageId);
    }
}

