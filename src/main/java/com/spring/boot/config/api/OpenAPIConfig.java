package com.spring.boot.config.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "scVMAP API", description = "API of scVMAP platform"))
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("scVMAP API")
                        .description("API of scVMAP platform")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://bio.liclab.net")));
    }

}
