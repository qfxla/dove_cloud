package com.dove.breed.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dove.breed.entity.CagePicture;
import com.dove.breed.entity.CagePosition;
import com.dove.breed.service.CagePictureService;
import com.dove.breed.service.CagePositionService;
import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import io.swagger.annotations.Api;
import okhttp3.*;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 鸽笼图片表 前端控制器
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@Api(tags = "鸽笼图片表")
@RestController
@RequestMapping("/breed/cage-picture")
public class CagePictureController {
    @Autowired
    private CagePictureService cagePictureService;
    @Autowired
    private CagePositionService cagePositionService;

    String UPLOAD_PATH = "http://120.77.156.205:9800/group1/upload";
    String PROCESS_PATH = "http://120.77.156.205:9808/api/v1/detect";

    @PostMapping("/image_upload")
    public Result upload2(MultipartFile image, @RequestParam("location_code")Long location_code,
                          @RequestParam("name")String name, @RequestParam("time")Date time) {
        QueryWrapper<CagePosition> wrapper = new QueryWrapper<>();
        wrapper.eq("cage_id",location_code);
        List<CagePosition> list = cagePositionService.list(wrapper);
        if (list.size() == 0){
            return Result.error("无该cageId");
        }
        String result = "";
        try {
            OkHttpClient httpClient = new OkHttpClient();
            MultipartBody multipartBody = new MultipartBody.Builder().
                    setType(MultipartBody.FORM)
                    .addFormDataPart("file", image.getOriginalFilename(),
                            RequestBody.create(MediaType.parse("multipart/form-data;charset=utf-8"),
                                    image.getBytes()))
                    .addFormDataPart("output", "json")
                    .addFormDataPart("scene","cageImage")
                    .build();

            Request request = new Request.Builder()
                    .url(UPLOAD_PATH)
                    .post(multipartBody)
                    .build();

            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    result = body.string();
                    System.out.println(result);
                    JSONObject jsonObject = JSON.parseObject(result);
                    String path = (String)jsonObject.get("path");
                    CagePicture cagePicture = new CagePicture();
                    cagePicture.setCageId(location_code);
                    cagePicture.setPic(path);
                    cagePicture.setPicName(name);
                    cagePicture.setTime(time);


                    MediaType JSON1 = MediaType
                            .parse("application/json; charset=utf-8");
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("img_path",path);
                    String s = JSON.toJSONString(jsonObject1);
                    String processResult = "";
                    Request processRequest = new Request.Builder()
                            .url(PROCESS_PATH)
                            .post(RequestBody.create(JSON1,s))
                            .build();

                    Response processResponse = httpClient.newCall(processRequest).execute();
                    System.out.println(processResponse);
                    System.out.println(processResponse.body());
                    if (processResponse.isSuccessful()){
                        ResponseBody processBody = processResponse.body();
                        if (processBody != null){
                            processResult = processBody.string();
                            System.out.println("加工返回体：" + processResult);
                            JSONObject jsonObject2 = JSON.parseObject(processResult);
                            String processPath = (String)jsonObject2.get("img_save_path");
                            System.out.println("加工路径" + processPath);
                            cagePicture.setProcessPic(processPath);
                        }
                    }else {
                        System.out.println("加工接口调用失败");
                    }

                    boolean save = cagePictureService.save(cagePicture);

                    if (!save){
                        throw new GlobalException(StatusCode.ERROR,"图片存入出错");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result != ""? Result.success("保存图片成功").data(result) : Result.error(StatusCode.UPLOADERROT,"保存图片失败");
    }

//    @GetMapping
//    public Result process(String path) throws IOException {
//
//        MediaType JSON1 = MediaType
//                .parse("application/json; charset=utf-8");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("img_path",path);
//        String s = JSON.toJSONString(jsonObject);
//        OkHttpClient httpClient = new OkHttpClient();
//        String processResult = "";
//        Request processRequest = new Request.Builder()
//                .url(PROCESS_PATH)
//                .post(RequestBody.create(JSON1,s))
//                .build();
//
//        Response processResponse = httpClient.newCall(processRequest).execute();
//        System.out.println(processResponse);
//        System.out.println(processResponse.body());
//        if (processResponse.isSuccessful()){
//            ResponseBody processBody = processResponse.body();
//            if (processBody != null){
//                processResult = processBody.string();
//                System.out.println("加工返回体：" + processResult);
//                JSONObject jsonObject1 = JSON.parseObject(processResult);
//                String processPath = (String)jsonObject1.get("img_save_path");
//                System.out.println("加工路径" + processPath);
//            }
//        }else {
//            System.out.println("加工接口调用失败");
//        }
//
//        return Result.success("hh");
//    }
}

