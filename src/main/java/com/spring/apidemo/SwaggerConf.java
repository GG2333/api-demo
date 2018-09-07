package com.spring.apidemo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConf {

    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(newApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spring.apidemo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo newApiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger")
                .description("Swagger UI")
                .contact(new Contact("Swagger", "https://swagger.io", "email"))
                .version("1.0")
                .build();
    }
}
