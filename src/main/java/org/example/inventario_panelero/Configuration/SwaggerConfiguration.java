package org.example.inventario_panelero.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuración de Swagger que define y personaliza la
// documentación OpenAPI del sistema de inventario
@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title ("API INVENTARIO")
                        .version ("1.0")
                        .description ("API PARA SISTEMA DE INVENTARIO")
                        .contact(new Contact()
                                .name ("DESARROLLADOR DE SISTEMA DE INVENTARIO")
                                .email("prueba@prueba.com")));

    }

}