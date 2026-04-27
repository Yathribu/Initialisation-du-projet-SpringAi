package com.example.prototypeai.security.jwtutil;

import com.example.prototypeai.constants.Constants;
import com.example.prototypeai.user.entity.AiUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    public String generateJwtToken(Authentication authentication) {
        String jwtToken;
        String secret = Constants.JWT_SECRET_DEFAULT_VALUE;
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        AiUser aiUser = (AiUser) authentication.getPrincipal();
        jwtToken = Jwts.builder()
                .issuer("Prototype Ai")
                .subject("JWT Token") // Ou ID de l'utilisateur ?
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
