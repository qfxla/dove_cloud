package com.dove.breed.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zcj
 * @creat 2021-10-06-14:40
 */
@ControllerAdvice
@ResponseBody
public class GlobalHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

    // @InitBinder标注的initBinder()方法表示注册一个Date类型的类型转换器，用于将类似这样的2019-06-10
    // 日期格式的字符串转换成Date对象
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        logger.info("initBinder被调用了");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); //Lenient:宽松
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
    }
}
