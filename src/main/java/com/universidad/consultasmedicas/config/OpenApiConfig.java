package com.universidad.consultasmedicas.config;
import io.swagger.v3.oas.models.*; import io.swagger.v3.oas.models.info.*; import io.swagger.v3.oas.models.security.*; import org.springframework.context.annotation.*;
@Configuration public class OpenApiConfig {
 @Bean OpenAPI apiDocs(){return new OpenAPI().info(new Info().title("Sistema de consultas médicas").version("1.0.0").description("API segura por roles para gestión de médicos y citas")).components(new Components().addSecuritySchemes("basicAuth",new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))).addSecurityItem(new SecurityRequirement().addList("basicAuth"));}
}
