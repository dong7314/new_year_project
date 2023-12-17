package com.project.year.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi user() {
        String[] packagesToScan = {"com.project.year.user"};

        return GroupedOpenApi.builder()
                .group("user")
                .packagesToScan(packagesToScan)
                .build();
    }

    @Bean
    public OpenAPI openApiConfig() {
        Info info = new Info()
                .title("신년 새해 편지")
                .version("v1.0.0")
                .description("신년 새해 편지의 API 명세서입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
