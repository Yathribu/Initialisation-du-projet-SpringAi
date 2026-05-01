package com.example.prototypeai.ratelimit;

import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.entity.AiRequest;
import com.example.prototypeai.ai.repository.AiRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RateLimit {

    private final AiRequestRepository aiRequestRepository;

    public boolean isAuthorizedToPrompt(AiRequestDto.PostInput request) {
        int limitRateDelay = 15;
        int amountOfPromptLimit = 10;

        Instant windows = Instant.now().minus(Duration.ofMinutes(limitRateDelay));
        List<AiRequest> listOfRequest = aiRequestRepository.findByUserIdAndCreatedAtAfter(request.userId(), windows);

        return listOfRequest.size() <= amountOfPromptLimit;
    }
}
