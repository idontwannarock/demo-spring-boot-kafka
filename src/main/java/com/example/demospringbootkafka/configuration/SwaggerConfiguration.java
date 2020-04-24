package com.example.demospringbootkafka.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean("docket")
    public Docket docket() {
        return new Docket(SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(basePackage("com.example.demospringbootkafka.controller"))
                .paths(any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Kafka Demo API")
                .version("1.0")
                .build();
    }
}
