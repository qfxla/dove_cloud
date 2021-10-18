package com.dove.breed.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import springfox.documentation.spring.web.json.Json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-10-09-15:09
 */
public class GetFileData {

    public static List<Object> getJsonObject(String path){
        List<Object> list = new ArrayList<>();
        try {
            File file = new File(path);
            String stringData = FileUtils.readFileToString(file, "UTF-8");
            //因为文件中对象我统一用数组包起来了，所以用这个
            list = (List<Object>)JSONArray.parse(stringData);
        }catch (Exception e){
            System.out.println("我报错了");
            e.printStackTrace();
        }
        return list;
    }

}
