package com.example.prototypeai.ai.service;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.orchestrateur.AiOrchestror;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestToAi {

    private final AiOrchestror orchestrator;

    @Cacheable(value = "aiResponse", key = "@keyCacheBuilder.buildKey(#request, #providers)")
    public List<String> getAiResponse(AiRequestDto.PostInput request, List<AskAi> providers) {
        return orchestrator.executePrompt(request, providers);
    }

}
