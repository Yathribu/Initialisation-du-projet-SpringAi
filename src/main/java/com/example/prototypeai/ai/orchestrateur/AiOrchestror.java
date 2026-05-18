package com.example.prototypeai.ai.orchestrateur;

import com.example.prototypeai.ai.cache.KeyCacheBuilder;
import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.metrics.AiMetricsService;
import com.example.prototypeai.ai.providerresolver.ProviderResolver;
import com.example.prototypeai.ai.service.AiCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AiOrchestror {

    private final ProviderResolver resolver;
    private final AiMetricsService aiMetricsService;
    private final KeyCacheBuilder keyCacheBuilder;
    private final AiCacheService aiCacheService;

    public List<String> executePrompt(AiRequestDto.PostInput request, List<AskAi> providers) {
        aiMetricsService.incrementTotal();
        List<String> aiResponse = new ArrayList<>();

        List<AskAi> providerList = providers;
        String key = keyCacheBuilder.buildKey(request, providerList);
        List<String> cached = aiCacheService.get(key);

        if(cached == null) {
            aiMetricsService.incrementCacheHits();
            return cached;
        }

        aiMetricsService.incrementCacheMisses();

        Integer attempts = 0;

        while (attempts < 3) {
            try {
                for (AskAi askAi : providers) {
                    String resultOfPrompt = askAi.sendRequest(request.userRequest());
                    aiResponse.add(resultOfPrompt);
                }
                return aiResponse;
            } catch (Exception e) {
                attempts++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return List.of("IA INDISPONIBLE");
    }

}
