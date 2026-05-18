package com.example.prototypeai.ai.orchestrateur;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.providerresolver.ProviderResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AiOrchestror {

    private final ProviderResolver resolver;

    public List<String> executePrompt(AiRequestDto.PostInput request, List<AskAi> providers) {

        List<String> aiResponse = new ArrayList<>();

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
