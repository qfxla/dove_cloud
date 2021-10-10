package com.dove.breed.controller.ui;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.Dovecote;
import com.dove.breed.entity.ShipmentOutBill;
import com.dove.breed.mapper.DovecoteMapper;
import com.dove.breed.service.DovecoteOutBaseService;
import com.dove.breed.service.DovecoteOutBillService;
import com.dove.breed.service.DovecoteService;
import com.dove.breed.service.ShipmentOutBillService;
import com.dove.breed.utils.GetFileData;
import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.symmetric.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Watchable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zcj
 * @creat 2021-10-10-14:42
 */
@CrossOrigin
@Slf4j
@Api(tags = "ui-基地图")
@RestController
@RequestMapping("/ui/shipment")
public class ShipmentController {
    @Value("${BASE_UI_URL.shipment}")
    public String baseUrl;

    @Autowired
    private DovecoteOutBaseService dovecoteOutBaseService;
    @Autowired
    private DovecoteOutBillService dovecoteOutBillService;
    @Autowired
    private ShipmentOutBillService shipmentOutBillService;
    @Autowired
    private DovecoteMapper dovecoteMapper;

    @ApiOperation("肉鸽出栏曲线图")
    @GetMapping("getOutOfBreedingDove")
    public Result getBreedingDove(){
        String path = baseUrl + "肉鸽出栏.txt";
        System.out.println(path);
        QueryWrapper<ShipmentOutBill> wrapper = new QueryWrapper<>();
        List<ShipmentOutBill> list = shipmentOutBillService.list(wrapper);
        Map<Integer, List<ShipmentOutBill>> map = list.stream().collect(
                Collectors.groupingBy(
                        shipmentOutBill -> shipmentOutBill.getGmtCreate().getMonth()
                ));
        Map<Integer, Integer> map1 = new HashMap<>();
        for (Map.Entry<Integer, List<ShipmentOutBill>> entrySet : map.entrySet()) {
            int total = 0;
            for (ShipmentOutBill shipmentOutBill : entrySet.getValue()) {
                total += shipmentOutBill.getTotal();
            }
            map1.put(entrySet.getKey() + 1,total);
        }
        return map1.size() > 0?Result.success("获取成功").data(map1) : Result.error(StatusCode.ERROR,"文件不存在或无数据");
    }

    @ApiOperation("异常情况")
    @GetMapping("abnormal")
    public Result abnormal(){
        String path = baseUrl + "肉鸽出栏.txt";
        System.out.println(path);
        //基地异常曲线图
        List<Map<String, Object>> list = dovecoteMapper.uiGetAbnormalOfShipment(3L);
        return list.size() > 0? Result.success("获取成功").data(list) : Result.error("获取失败");
    }

    @ApiOperation("鸽龄分布")
    @GetMapping("doveAge")
    public Result doveAge(){
        String path = baseUrl + "鸽龄分布.txt";
        System.out.println(path);
        return null;
    }
}
