package com.example.prototypeai.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class PathConfig {

    @Bean(name = "publicPaths")
    public List<String> publicPath() {
        return List.of(
                "/auth/register/public",
                "/auth/login/public"
        );
    }

    @Bean(name = "securedPaths")
    public List<String> securedPath() {
        return List.of(
                "/..."
        );
    }

}
