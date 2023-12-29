package com.paygoal.apiproductos.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi productAPI(){
        final String[] packagesToScan = {"com.paygoal.apiproductos.controller"};
        return GroupedOpenApi
                .builder()
                .group("com.paygoal")
                .packagesToScan(packagesToScan)
                .addOpenApiCustomizer(statusApiCostumizer())
                .build();
    }
    private OpenApiCustomizer statusApiCostumizer() {
        return openAPI -> openAPI
                .info(new Info()
                        .title("Product API RESTful @PayGoal")
                        .description("This is a sample Spring Boot RESTful service using OpenAPI")
                        .version("3.0.0")
                        .contact(new Contact()
                                .name("Gino Paoletti")
                                .url("https://www.linkedin.com/in/paolettigino/")
                                .email("ginopaolettigeuna30@gmail.com")));
    }
}
