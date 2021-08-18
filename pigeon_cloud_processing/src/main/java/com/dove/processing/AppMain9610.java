package com.dove.processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 小亮
 * @date 2021/8/17  -  20:48
 */

@SpringBootApplication
//@EnableDiscoveryClient
@ComponentScan("com.dove")
public class AppMain9610 {
    public static void main(String[] args) {
        SpringApplication.run(AppMain9610.class,args);
    }
}
