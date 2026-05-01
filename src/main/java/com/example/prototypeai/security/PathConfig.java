package com.example.prototypeai.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class PathConfig {

    @Bean(name = "publicPaths")
    public List<String> publicPath() {
        return List.of(
                "/auth/public/register",
                "/auth/public/login",
                "/home/public/all",
                "/home/public/compare"
        );
    }

    @Bean(name = "securedPaths")
    public List<String> securedPath() {
        return List.of(
                "/..."
        );
    }

}
