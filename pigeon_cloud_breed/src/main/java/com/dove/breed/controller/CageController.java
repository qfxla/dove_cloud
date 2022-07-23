package com.dove.breed.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.excel.CageExcel;
import com.dove.breed.mapper.CageMapper;
import com.dove.breed.utils.ExcelWidthStyleStrategy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.script.ScriptTemplateConfig;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@CrossOrigin
@Api(tags = "鸽笼历史表")
@RestController
@RequestMapping("/breed/cage")
public class CageController {
    @Autowired
    private CageMapper cageMapper;


    @ApiOperation(value = "导出操作",notes = "export",produces = "application/octet-stream")
    @GetMapping("exportData")
    public void exportCageOperation(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码，所有通过后端的文件下载都可以如此处理
        String fileName = URLEncoder.encode("鸽笼操作", "UTF-8");
        //建议加上该段，否则可能会出现前端无法获取Content-disposition
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<JSONObject> jsonObjects = cageMapper.exportData();
        ArrayList<CageExcel> cageExcels = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects) {
            CageExcel cageExcel = new CageExcel();
            cageExcel.setBc_no(jsonObject.get("bc_no").toString());
            cageExcel.setColumn_no(Integer.valueOf(jsonObject.get("column_no").toString()));
            cageExcel.setLine(Integer.valueOf(jsonObject.get("line").toString()));
            cageExcel.setRow_no(Integer.valueOf(jsonObject.get("row_no").toString()));
            cageExcel.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jsonObject.get("create_time").toString()));
            if(jsonObject.get("is_end") != null) {
                cageExcel.setIs_end("是");
                cageExcel.setState("无");
                cageExcel.setAbnormal("无");
                cageExcel.setXf("无");
            }else {
                cageExcel.setIs_end("否");
            }
            StringBuilder abnormal = new StringBuilder("");
            String state ;
            String xf;
            switch (Integer.valueOf(jsonObject.get("state").toString())){
                case 0 :
                    state = "无蛋";
                    break;
                case 1 :
                    state = "下一步照蛋";
                    break;
                case 2 :
                    state = "下一步抽蛋";
                    break;
                case 3 :
                    state = "已抽蛋";
                    break;
                case 4:
                    state = "已照蛋";
                    break;
                default:
                    state = "已查仔";
                    break;
            }
            cageExcel.setState(state);
            if(jsonObject.get("abnormal") == null){
                cageExcel.setAbnormal("无异常");
            }else {
                if(Integer.valueOf(jsonObject.get("state").toString()) == 0 ||
                        Integer.valueOf(jsonObject.get("state").toString()) == 1 ||
                        Integer.valueOf(jsonObject.get("state").toString()) == 2){
                    cageExcel.setAbnormal("无异常");
                }else if(Integer.valueOf(jsonObject.get("state").toString()) == 3 ||
                        Integer.valueOf(jsonObject.get("state").toString()) == 4){
                    switch (Integer.valueOf(jsonObject.get("state").toString())){
                        case 0 :
                            abnormal.append("两个蛋");
                            break;
                        case 1 :
                            abnormal.append("单蛋");
                            break;
                        case 2 :
                            abnormal.append("未受精1");
                            break;
                        case 3 :
                            abnormal.append("未受精2");
                            break;
                        case 4 :
                            abnormal.append("踩蛋1");
                            break;
                        default:
                            abnormal.append("踩蛋2");
                    }
                }else if(Integer.valueOf(jsonObject.get("state").toString()) == 5){
                    //5的话有两位
                    int diyiwei = Integer.valueOf(jsonObject.get("state").toString()) % 10;
                    int dierwei = Integer.valueOf(jsonObject.get("state").toString()) / 10;
                    switch (diyiwei){
                        case 0 :
                            abnormal.append("正常蛋");
                            break;
                        case 1 :
                            abnormal.append("臭蛋1");
                            break;
                        case 2 :
                            abnormal.append("臭蛋2");
                            break;
                    }
                    switch (dierwei){
                        case 1 :
                            abnormal.append("有1仔");
                            break;
                        case 2 :
                            abnormal.append("有2仔");
                            break;
                        case 3 :
                            abnormal.append("有3仔");
                            break;
                        case 4 :
                            abnormal.append("有4仔");
                            break;
                    }
                }
            }

            switch (Integer.valueOf(jsonObject.get("xf").toString())){
                case 0 :
                    xf = "未亮";
                    break;
                case 1 :
                    xf = "照蛋灯亮";
                    break;
                case 2 :
                    xf = "抽蛋灯亮";
                    break;
                case 3:
                    xf = "查仔灯亮";
                    break;
                case 4 :
                    xf = "临时等";
                    break;
                default:
                    xf = "未开始新流程";
                    break;
            }
            cageExcel.setXf(xf);
            cageExcels.add(cageExcel);
        }
        try {
            EasyExcel.write(response.getOutputStream(),CageExcel.class).sheet("鸽笼操作")
                    .registerWriteHandler(new ExcelWidthStyleStrategy()) // 设置宽度
                    .doWrite(cageExcels);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

