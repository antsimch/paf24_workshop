package sg.edu.nus.iss.paf24_workshop.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {
    
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("PAF24 Workshop")
                .description("Orders API")
                .version("version 1.0"));   
    }
}
