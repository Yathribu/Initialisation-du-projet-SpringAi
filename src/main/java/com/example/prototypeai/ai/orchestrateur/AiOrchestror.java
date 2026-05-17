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

        for (AskAi askAi : providers) {
            String resultOfPrompt = askAi.sendRequest(request.userRequest());
            aiResponse.add(resultOfPrompt);
        }

        return aiResponse;
    }

}
