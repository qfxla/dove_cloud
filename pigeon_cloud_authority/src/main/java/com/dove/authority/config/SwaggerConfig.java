package com.dove.authority.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author run
 * @since 2021/3/19 19:52
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){

        ParameterBuilder xip = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        xip.name("xip").description("ipAddress")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(xip.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(apiInfo())
                .select()
                //.paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("数字鸽业")
                .description("溯源不积极，思想有问题")
                .version("1.0") // 这里看着写
                .contact(new Contact("run", "https://leetcode-cn.com", "1842841029@qq.com"))
                .build();
    }
}