package ru.sfedu.simplepsyspecialist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Разрешаем все пути
                .allowedOrigins("*")  // Разрешаем запросы с любых источников
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Разрешаем все методы
                .allowedHeaders("*");  // Разрешаем все заголовки
    }
}
