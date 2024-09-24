package com.resourceserver.emazonshoppingcartservice.configuration.openapi.config;

import com.resourceserver.emazonshoppingcartservice.configuration.openapi.constants.OpenApiConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title(OpenApiConstants.OPENAPI_TITLE)
                        .version(OpenApiConstants.OPENAPI_VERSION)
                        .description(OpenApiConstants.OPENAPI_DESCRIPTION)

                );

    }
}
