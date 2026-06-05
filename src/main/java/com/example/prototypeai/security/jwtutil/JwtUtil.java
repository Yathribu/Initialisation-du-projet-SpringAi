package com.example.prototypeai.security.jwtutil;

import com.example.prototypeai.config.ConfigJwt;
import com.example.prototypeai.user.entity.AiUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final ConfigJwt  configJwt;
    private final Environment env;

    @Value("${app.jwtutil.issuer:Prototype Ai}")
    private String issuer;

    @Value("${app.jwtutil.subject:JWT Token}")
    private String subject;

    public String generateJwtToken(Authentication authentication) {

        String jwtToken;
        String secret = configJwt.getSecretKey();
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        AiUser aiUser = (AiUser) authentication.getPrincipal();
        jwtToken = Jwts.builder()
                .issuer(issuer)
                .subject(subject)
                .claim("name", aiUser.getName())
                .claim("email", aiUser.getEmail())
                .claim("numeroDeTelephone", aiUser.getNumeroDeTelephone())
                .claim("role", extractRoles(authentication))
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(Duration.ofDays(1))))
                .signWith(secretKey)
                .compact();
        return jwtToken;
    }

    private String extractRoles(Authentication auth) {
        return auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }


}
