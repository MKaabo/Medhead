package com.medhead.api.controller.configuration;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

public class SwaggerConfig {
    @Bean
    public Docket scrumAllyApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Medhead API")
                .description("Medhead API is a web application designed for emergency handling and hospital beds management.")
                .build();
    }
}
