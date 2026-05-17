package com.example.prototypeai.ai.cache;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.ai.dto.AiRequestDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KeyCacheBuilder {

    public String buildKey(AiRequestDto.PostInput request, List<AskAi> providers) {
        String provider = providers.stream()
                                   .map(ai -> ai.getProvider().name())
                                   .sorted()
                                   .collect(Collectors.joining(","));

        // Ici, pas besoin de hash la clé, sauf cas sécurité on est d'accord ?
        String requestNormalized = request.userRequest().trim().replaceAll("\\s+", " ");

        return  requestNormalized + "|PROVIDERS: " + provider;
    }

}
