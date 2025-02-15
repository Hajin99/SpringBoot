package com.example.shop;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {
    @Bean
    public Dotenv dotenv() {
        return Dotenv.load(); // 🔥 .env 파일 로드
    }
}
