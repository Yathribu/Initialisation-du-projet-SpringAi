package com.example.prototypeai.ai.providerresolver;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.util.enums.RequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProviderResolver {

    private final List<AskAi> askAiList;

    public List<AskAi> selectedAiFromUser(List<RequestType> aiChosenByUser) {

        if (aiChosenByUser.isEmpty() || aiChosenByUser == null) {
            return List.of(askAiList.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("L'ia selectionné n'existe pas")));
        }

        List<AskAi> pool = askAiList.stream().filter(p -> aiChosenByUser.contains(p.getProvider())).toList();
        if (pool.isEmpty()) {
            throw new IllegalArgumentException("Ia non retrouvé");
        }
        return pool;
    }

}
