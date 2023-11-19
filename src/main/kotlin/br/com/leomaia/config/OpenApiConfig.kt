package br.com.leomaia.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenApi(): OpenAPI{
        return OpenAPI()
            .info(
                Info()
                    .title("Restful API with Kotlin 1.6.10 and Spring boot 3.0.0")
                    .version("v1")
                    .description("Some description about the api!")
                    .termsOfService("exampleurl.com")
                    .license(
                        License().name("MIT")
                    )
            )
    }
}