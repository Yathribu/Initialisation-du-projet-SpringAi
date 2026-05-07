package com.example.prototypeai.ratelimit;

import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.repository.AiRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class RateLimit {

    private final AiRequestRepository aiRequestRepository;

    public boolean isAuthorizedToPrompt(AiRequestDto.PostInput request) {
        int limitRateDelay = 15;
        int amountOfPromptLimit = 10;

        Instant windows = Instant.now().minus(Duration.ofMinutes(limitRateDelay));
        Integer count = aiRequestRepository.countByUserIdAndCreatedAtAfter(request.userId(), windows);

        return count <= amountOfPromptLimit;
    }
}
