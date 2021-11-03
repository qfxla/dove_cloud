package com.dove.breed.controller.bigdata;

import com.alibaba.fastjson.JSONObject;
import com.dove.breed.entity.ShipmentOutBill;
import com.dove.breed.mapper.DovecoteDailyMapper;
import com.dove.breed.service.DovecoteDailyService;
import com.dove.breed.service.ShipmentOutBillService;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-28-10:30
 */
@Api(tags = "大数据可视化")
@CrossOrigin
@RestController
@RequestMapping("/breed/dataVisual")
public class DataVisual {

    @Autowired
    private ShipmentOutBillService shipmentOutBillService;

    @Autowired
    private DovecoteDailyService dovecoteDailyService;


    @ApiOperation("概览")
    @GetMapping("overview")
    public Result overview(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amountOfBase",1586);
        jsonObject.put("amountOfProcess",15);
        jsonObject.put("amountOfStaff",2737);
        jsonObject.put("amountOfBill",15862);
        jsonObject.put("turnover",15862);
        return Result.success("获取成功").data(jsonObject);
    }

    @ApiOperation("交易数省份")
    @GetMapping("dealAmountOfProvince")
    public Result dealAmountOfProvince(){
        ArrayList<JSONObject> list = new ArrayList<>();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("province","广东省");
        jsonObject1.put("amount",9000);
        list.add(jsonObject1);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("province","湖南省");
        jsonObject2.put("amount",9500);
        list.add(jsonObject2);
        JSONObject jsonObject3= new JSONObject();
        jsonObject3.put("province","江西省");
        jsonObject3.put("amount",7000);
        list.add(jsonObject3);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("province","河南省");
        jsonObject4.put("amount",8000);
        list.add(jsonObject4);
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("province","北京省");
        jsonObject5.put("amount",6000);
        list.add(jsonObject5);
        return Result.success("获取成功").data(list);
    }

    @ApiOperation("交易类型(百分比)")
    @GetMapping("tradeType")
    public Result tradeType(){
        ArrayList<JSONObject> list = new ArrayList();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("type","中通");
        jsonObject1.put("amount",30);
        list.add(jsonObject1);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("type","天猫");
        jsonObject2.put("amount",10);
        list.add(jsonObject2);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("type","顺丰");
        jsonObject3.put("amount",20);
        list.add(jsonObject3);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("type","京东");
        jsonObject4.put("amount",20);
        list.add(jsonObject4);
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("type","圆通");
        jsonObject5.put("amount",20);
        list.add(jsonObject5);
        return Result.success("获取成功").data(list);
    }

    @ApiOperation("销售额")
    @GetMapping("saleroom")
    public Result saleroom(){
        ArrayList<JSONObject> list = new ArrayList();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("type","daySale");
        jsonObject1.put("amount",34);
        list.add(jsonObject1);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("type","weekSale");
        jsonObject2.put("amount",123);
        list.add(jsonObject2);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("type","monthSale");
        jsonObject3.put("amount",1929);
        list.add(jsonObject3);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("type","yearSale");
        jsonObject4.put("amount",28273);
        list.add(jsonObject4);
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("type","totalSale");
        jsonObject5.put("amount",8232873);
        list.add(jsonObject5);
        return Result.success("获取成功").data(list);
    }

    @ApiOperation("销售详情(月)")
    @GetMapping("saleDetails")
    public Result saleDetails(){
        List<JSONObject> list = shipmentOutBillService.getAllTypeAmountOfMonth(3L, 2021);
        return Result.success("获取成功").data(list);
    }

    @ApiOperation("鸽子异常数(月)")
    @GetMapping("doveAbnormal")
    public Result doveAbnormal() throws JSONException {
        List<JSONObject> list = dovecoteDailyService.getDoveAbnormal();
        return Result.success("获取成功").data(list);
    }
}
