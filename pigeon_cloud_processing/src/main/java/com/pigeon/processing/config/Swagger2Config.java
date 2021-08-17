package com.pigeon.processing.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author ZZF
 * @Time 2021/03/22 13:12
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi(Environment environment){
//        Profiles profiles = Profiles.of("dev", "test");
//        boolean enableSwagger = environment.acceptsProfiles(profiles);
//        //如果是dev或者test则开启swagger，否则关闭,开发时用
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .enable(enableSwagger)
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("数字鸽业")
                .description("哈哈哈")
                .version("1.0")
                .contact(new Contact("尽管努力的新人", "https://leetcode-cn.com", "1401454390@qq.com"))
                .build();
    }

}
