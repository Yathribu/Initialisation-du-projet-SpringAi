package com.example.prototypeai.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineCacheConfig {

    @Bean
    public CacheManager caffeineCacheManager() {

        CaffeineCache caffeineCache = new CaffeineCache("aiResponse", Caffeine.newBuilder()
                                                                                .expireAfterWrite(10, TimeUnit.MINUTES)
                                                                                .maximumSize(100)
                                                                                .build());
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(List.of(caffeineCache));
        return simpleCacheManager;
    }

}
