package com.dove.breed.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-20-11:38
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2DateConverter());
    }

    class String2DateConverter implements Converter<String, Date> {
        @SneakyThrows
        public Date convert(String s) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(s);
        }
    }

}
