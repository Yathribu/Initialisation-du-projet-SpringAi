package com.example.prototypeai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class ConfigJwt {

    private String secretKey;
    private int tokenValiditySeconds;
    private String issuer;

}
