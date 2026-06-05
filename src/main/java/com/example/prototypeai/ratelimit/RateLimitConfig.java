package com.example.prototypeai.ratelimit;

import com.ratelimiterspringcore.ratelimit.RateLimiter;
import com.ratelimiterspringcore.ratelimit.RateLimiterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@RequiredArgsConstructor
public class RateLimitConfig {

    @Bean
    public RateLimiter rateLimiter() {
        return new RateLimiterImpl();
    }

    /*private final AiRequestRepository aiRequestRepository;

    public boolean isAuthorizedToPrompt(AiRequestDto.PostInput request) {
        int limitRateDelay = 15;
        int amountOfPromptLimit = 10;

        Instant windows = Instant.now().minus(Duration.ofMinutes(limitRateDelay));
        Integer count = aiRequestRepository.countByAiUserIdAndCreatedAtAfter(request.userId(), windows);

        return count <= amountOfPromptLimit;
    }*/

}
