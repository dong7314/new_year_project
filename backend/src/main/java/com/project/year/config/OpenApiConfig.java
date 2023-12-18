package com.project.year.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi user() {
        String[] packagesToScan = {"com.project.year"};

        return GroupedOpenApi.builder()
                .group("user")
                .packagesToScan(packagesToScan)
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("신년 새해 편지")
                .description("신년 새해 편지의 API 명세서입니다.")
                .version("v1.0.0"));
    }
}
