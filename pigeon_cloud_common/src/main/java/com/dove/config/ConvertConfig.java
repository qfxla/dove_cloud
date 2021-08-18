package com.dove.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author run
 * @since 2021/4/1 19:59
 */
@Configuration
public class ConvertConfig {

    //HttpMessageConverter是用来处理request和response里的数据的
    @Bean
    public HttpMessageConverters customConverters() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //ObjectMapper可以从字符串、流或文件解析JSON，并创建Java对象或对象图来表示已解析的JSON
        ObjectMapper objectMapper = new ObjectMapper();
        //用于帮助objectMapper注册，帮助实现自定义扩展模块的类和接口(使用objectapper . registermodule注册)
        SimpleModule simpleModule = new SimpleModule();
        //JSON Long ==> String(因为Long类型数据直接传给前端，容易发生精度损失，统一处理）
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        //设置日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        return new HttpMessageConverters(new HttpMessageConverter[]{mappingJackson2HttpMessageConverter});
    }
}
