package com.example.prototypeai.ai.service;

import com.example.prototypeai.ai.dto.AskAiDto;
import com.example.prototypeai.ai.model.AiRequest;
import com.example.prototypeai.client.AskAi;
import com.example.prototypeai.enums.AiProvider;
import com.example.prototypeai.ai.repository.AiInteractionRepository;
import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@EntityListeners(AuditingEntityListener.class)
public class AskAiService {

    private final AiInteractionRepository aiInteractionRepository;
    private final Map<AiProvider.RequestType, AskAi> aiMap;

    public AskAiService(AiInteractionRepository aiInteractionRepository, Map<AiProvider.RequestType, AskAi> aiMap) {
        this.aiInteractionRepository = aiInteractionRepository;
        this.aiMap = aiMap;
    }

    public AskAiDto.PostOutput sendRequest(String request, AiProvider.RequestType requestType) {

        AskAi ai = aiMap.get(requestType);

        if(ai == null) {
            throw new RuntimeException("No such AskAi");
        }

        AiRequest aiInteraction = new AiRequest();
        aiInteraction.setRequest(request);
        aiInteraction.setRequestCreatedAt(LocalDateTime.now());
        aiInteraction.setRequestType(requestType);
        aiInteraction.setResponse(ai.sendRequest(request));
        aiInteractionRepository.save(aiInteraction);

        return AskAiDto.PostOutput.builder()
                .userResponse(aiInteraction.getResponse())
                .build();
    }

}
