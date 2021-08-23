package com.dove.processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 小亮
 * @date 2021/8/17  -  20:48
 */

@SpringBootApplication(scanBasePackages = "com.dove")
@EnableSwagger2 //注意要在启动类上加上这个注解
public class AppMain9806 {
    public static void main(String[] args) {
        SpringApplication.run(AppMain9806.class,args);
    }
}
