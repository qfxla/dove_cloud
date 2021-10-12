package com.dove.breed.controller.ui;

import com.dove.breed.entity.vo.CageRealVo;
import com.dove.breed.mapper.CageRealMapper;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.mapper.DovecoteOutBillMapper;
import com.dove.breed.service.DovecoteService;
import com.dove.breed.utils.GetFileData;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zcj
 * @creat 2021-10-10-23:00
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-鸽棚图")
@RestController
@RequestMapping("/ui/dovecote")
public class DovecoteUiController {
    @Value("${BASE_UI_URL.dovecote}")
    public String baseUrl;

    @Autowired
    private DovecoteOutBillMapper dovecoteOutBillMapper;
    @Autowired
    private CageRealMapper cageRealMapper;
    @Autowired
    private DovecoteMapper dovecoteMapper;
    @Autowired
    private DovecoteService dovecoteService;

    @ApiOperation("肉鸽鸽龄分布")
    @GetMapping("doveAge")
    public Result doveAge(){
        String path = baseUrl + "鸽龄分布.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("肉鸽出栏曲线")
    @GetMapping("outOfBreedingDove")
    public Result outOfBreedingDove(){
        String path = baseUrl + "肉鸽出栏曲线.txt";
        System.out.println(path);
        //直接查一个基地总肉鸽出库数据即可，按月分
        List<Map<String, Object>> list = dovecoteOutBillMapper.uiOutOfBreedingDove(3L, "A01");
        return list.size() != 0?Result.success("获取成功").data(list) : Result.error("无数据");
    }

    @ApiOperation("鸽棚生产信息")
    @GetMapping("productionInformation")
    public Result productionInformation(){
        String path = baseUrl + "生产信息.txt";
        System.out.println(path);
        List<Object> jsonObject = GetFileData.getJsonObject(path);
        return jsonObject.size() > 0?Result.success("获取成功").data(jsonObject) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("鸽笼状态分布")
    @GetMapping("doveStatus")
    public Result doveStatus() {
        String path = baseUrl + "鸽笼状态.txt";
        System.out.println(path);
        List<CageRealVo> layEggsTime = cageRealMapper.getLayEggsTime(3L, "A01");
        List<CageRealVo> hatchTime = cageRealMapper.getHatchTime(3L, "A01");
        List<CageRealVo> feedTime = cageRealMapper.getFeedTime(3L, "A01");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("layTime",layEggsTime.size());
        map.put("hatchTime",hatchTime.size());
        map.put("feedTime",feedTime.size());
        return Result.success("获取成功").data(map);
    }

    @ApiOperation("鸽笼异常排行")
    @GetMapping("abnormalRanking")
    public Result abnormalRanking(){
        List<CageRealVo> cages = dovecoteService.getMaxAbnormal(3L, "A01", 1, 5);
        return cages.size() > 0? Result.success("获取成功").data(cages) : Result.error("获取失败");
    }
}
