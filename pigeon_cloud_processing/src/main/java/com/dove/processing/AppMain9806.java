package com.dove.processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 小亮
 * @date 2021/8/17  -  20:48
 */

@SpringBootApplication(scanBasePackages = "com.dove")
@EnableFeignClients
@EnableDiscoveryClient
public class AppMain9806 {
    public static void main(String[] args) {
        SpringApplication.run(AppMain9806.class,args);
    }
}
