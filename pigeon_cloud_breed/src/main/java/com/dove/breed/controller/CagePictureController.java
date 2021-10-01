package com.dove.breed.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dove.breed.service.CagePictureService;
import com.dove.breed.service.CagePositionService;
import com.dove.breed.utils.Image2Mp4;
import io.swagger.annotations.Api;
import okhttp3.*;
import org.bytedeco.javacv.FrameRecorder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    String UPLOAD_PATH = "http://120.77.156.205:9800/group1/upload";

    @PostMapping("/upload")
    public String upload2(MultipartFile file) {
        String result = "";
        try {
            OkHttpClient httpClient = new OkHttpClient();
            MultipartBody multipartBody = new MultipartBody.Builder().
                    setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getOriginalFilename(),
                            RequestBody.create(MediaType.parse("multipart/form-data;charset=utf-8"),
                                    file.getBytes()))
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
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}

