package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
// 开启Swagger2的自动配置
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger实例Bean是Docket，所以通过配置Docket实例来配置Swaggger
     * 配置docket以配置Swagger具体参数
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spring.swagger.controller"))
                //.paths(PathSelectors.ant("/hello/**"))
                .build();
    }

    /**
     * 可以通过apiInfo()属性配置文档信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger Demo")
                .description("学习 Swagger")
                // 联系人姓名 联系人访问链接 联系人邮箱
                .contact(new Contact("花开不合阳春暮", "https://www.baidu.com/", "457540867@qq.com"))
                .version("v1.0.0")
                // 组织链接
                .termsOfServiceUrl("https://swagger.io/")
                .license("Apach 2.0 许可")
                .licenseUrl("许可链接")
                .build();
    }


}
