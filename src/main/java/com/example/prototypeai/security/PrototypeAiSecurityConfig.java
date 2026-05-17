package com.example.prototypeai.security;

import com.example.prototypeai.security.jwtFilter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import java.util.List;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class PrototypeAiSecurityConfig {

    private final List<String> publicPaths;
    private final List<String> securedPaths;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public PrototypeAiSecurityConfig(@Qualifier("publicPaths") List<String> publicPaths,
                                     @Qualifier("securedPaths")List<String> securedPaths, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.publicPaths = publicPaths;
        this.securedPaths = securedPaths;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.authorizeHttpRequests(authorizeRequests -> {
                    publicPaths.forEach(path -> authorizeRequests.requestMatchers(path).permitAll());
                    securedPaths.forEach(path -> authorizeRequests.requestMatchers(path).authenticated());
                    authorizeRequests.anyRequest().denyAll();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}
