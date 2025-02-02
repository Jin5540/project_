package com.project.leeyejin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS 허용
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS") // 허용할 HTTP 메소드
                .allowCredentials(true);
    }
}

