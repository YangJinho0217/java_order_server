package star.inter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
        .title("test")
        .version("v1.0.0")
        .description("test API")
        ;

        return new OpenAPI()
        .components(new Components())
        .info(info)
        ;
    }
    
}
