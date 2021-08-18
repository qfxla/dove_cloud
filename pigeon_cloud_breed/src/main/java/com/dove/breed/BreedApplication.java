package com.dove.breed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zcj
 * @creat 2021-08-17-16:19
 */
@SpringBootApplication
@ComponentScan("com.dove")
@MapperScan("com.dove.breed.mapper")
public class BreedApplication {
    public static void main(String[] args) {
        SpringApplication.run(BreedApplication.class,args);
    }
}
