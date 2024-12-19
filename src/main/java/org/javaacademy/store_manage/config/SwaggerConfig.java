package org.javaacademy.store_manage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        Contact contact = new Contact()
                .email("ivan_senior@yandex.ru")
                .name("Ivan");

        Info info = new Info()
                .title("Система управления магазинами")
                .contact(contact)
                .description("Сервис интеграции для управления системами магазинов");

        return new OpenAPI()
                .info(info);
    }
}
