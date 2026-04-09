package com.example.prototypeai.ai.service;

import com.example.prototypeai.ai.dto.AskAiDto;
import com.example.prototypeai.ai.entity.AiRequest;
import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.user.entity.User;
import com.example.prototypeai.user.repository.IUserRepository;
import com.example.prototypeai.ai.repository.AiInteractionRepository;
import com.example.prototypeai.util.enums.RequestType;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class AskAiService {

    private final AiInteractionRepository aiInteractionRepository;
    private final IUserRepository userRepository;
    private final Map<RequestType, AskAi> aiMap;

    public AskAiService(AiInteractionRepository aiInteractionRepository, IUserRepository userRepository, Map<RequestType, AskAi> aiMap) {
        this.aiInteractionRepository = aiInteractionRepository;
        this.userRepository = userRepository;
        this.aiMap = aiMap;
    }

    public AskAiDto.PostOutput sendRequest(AskAiDto.PostInput request) {

        User user = userRepository.findById(request.userId()).orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        if(!isAvalaible(request)) {
            throw new IllegalArgumentException("Nombre de requête limité");
        }

        AskAi ai = aiMap.get(request.aiProvider());
        if(ai == null) {
            throw new RuntimeException("Aucune ia portant ce nom n'a été trouvé");
        }

        AiRequest aiProvider = new AiRequest();
        aiProvider.setRequest(request.userRequest());
        aiProvider.setRequestType(request.aiProvider());
        aiProvider.setResponse(ai.sendRequest(request.userRequest()));
        aiProvider.setUser(user);
        aiInteractionRepository.save(aiProvider);

        return AskAiDto.PostOutput.builder()
                .userResponse(aiProvider.getResponse())
                .build();
    }

    public boolean isAvalaible(AskAiDto.PostInput request) {
        Instant now = Instant.now();
        Duration windowsTime = Duration.ofMinutes(15);
        Instant windows = now.minus(windowsTime);
        List<AiRequest> listOfRequest = aiInteractionRepository.findByUserIdAndCreatedAtAfter(request.userId(), windows);
        return listOfRequest.size() <= 10;
    }

}
