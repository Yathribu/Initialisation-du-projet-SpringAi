package com.example.prototypeai.ai.orchestrateur;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.mod.ModStrategy;
import com.example.prototypeai.ai.mod.ModeStrategyResolver;
import com.example.prototypeai.ai.providerresolver.ProviderResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AiOrchestror {

    private final ProviderResolver resolver;
    private final ModeStrategyResolver modeStrategyResolver;

    public List<String> executePrompt(AiRequestDto.PostInput request) {

        List<AskAi> providers = resolver.selectedAiFromUser(request.aiProvider());

        ModStrategy strategy = modeStrategyResolver.get(request.mod());

        List<AskAi> selectedMod = strategy.applyMod(providers);

        List<String> aiResponse = new ArrayList<>();
        for (AskAi askAi : selectedMod) {
            String result = askAi.sendRequest(request.userRequest());
            aiResponse.add(result);
        }

        return aiResponse;
    }


}
