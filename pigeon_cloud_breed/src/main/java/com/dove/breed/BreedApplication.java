package com.dove.breed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zcj
 * @creat 2021-08-17-16:19
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan("com.dove")
@MapperScan("com.dove.breed.mapper")
@EnableDiscoveryClient
public class BreedApplication {
    public static void main(String[] args) {
        SpringApplication.run(BreedApplication.class,args);
    }
}
