package projeto_simples.demo.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Meu primeiro projeto")
                        .description("API exemplo de uso do Spring Boot")
                        .version("1.0")
                        .termsOfService("Termo de uso: Open Source")
                        .contact(new Contact()
                                .name("armando")
                                .url("https://seusite.com")
                                .email("exemplo@gmail.com"))
                        .license(new License()
                                .name("Licença - Sua Empresa")
                                .url("http://www.seusite.com"))
                );
    }
}
