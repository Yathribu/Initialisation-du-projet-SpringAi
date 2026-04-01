package com.example.prototypeai.ai.service;

import com.example.prototypeai.client.AskAi;
import com.example.prototypeai.enums.AiProvider;
import com.example.prototypeai.repository.AiInteractionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AskAiService {

    private final AiInteractionRepository aiInteractionRepository;

    List<AskAi> aiList;

    public AskAiService(AiInteractionRepository aiInteractionRepository, List<AskAi> aiList) {
        this.aiInteractionRepository = aiInteractionRepository;
        this.aiList = aiList;
    }

    public String sendRequest(String request, AiProvider.RequestType requestType) {

        for (AskAi askAi : aiList) {
            if(requestType == askAi.getProvider()){
                aiInteractionRepository.save(request, requestType);
                return askAi.sendRequest(request);
            }
        }

        throw new RuntimeException("No such AskAi");
    }

}
