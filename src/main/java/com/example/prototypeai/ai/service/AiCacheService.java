package com.example.prototypeai.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AiCacheService {

    private final CacheManager cacheManager;

    public List<String> get(String key) {
        Cache cache = cacheManager.getCache("aiResponse");
        return cache != null ? cache.get(key, List.class) : null;
    }

    public void put(String key, String value) {
        Cache cache = cacheManager.getCache("aiResponse");
        if (cache != null) {
            cache.put(key, value);
        }
    }

}
