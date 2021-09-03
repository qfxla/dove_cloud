package com.dove.processing.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 小亮
 * @date 2021/7/18  -  21:26
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dove.processing"))   //controller的路径
                .paths(PathSelectors.any())    //所有路径都生成文档
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//自己控制错误跳转页面
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("梅州鸽子")
                .description("哈哈哈")
                .version("1.0")
                .contact(new Contact("努力工作的小菜鸟", "http://baidu.com", "2057401688@qq.com"))
                .build();
    }

}
