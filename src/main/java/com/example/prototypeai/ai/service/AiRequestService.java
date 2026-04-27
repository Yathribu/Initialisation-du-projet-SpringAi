package com.example.prototypeai.ai.service;

import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.entity.AiRequest;
import com.example.prototypeai.ai.orchestrateur.AiOrchestror;
import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.user.repository.IAiUserRepository;
import com.example.prototypeai.ai.repository.AiRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiRequestService {

    private final Integer limitRateDelay = 15;
    private final Integer amountOfPromptLimit = 10;
    private final AiRequestRepository aiRequestRepository;
    private final IAiUserRepository userRepository;
    private final AiOrchestror orchestrator;

    public AiRequestDto.PostOutput sendRequest(AiRequestDto.PostInput request) {

        AiUser aiUser = userRepository.findById(request.userId()).orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        if(!isAuthorizedToPrompt(request)) {
            throw new IllegalArgumentException("Nombre de requête limité");
        }

        List<String> aiResponse = orchestrator.executePrompt(request);

        AiRequest aiRequest = AiRequest.builder().request(request.userRequest())
                                                 .requestType(request.aiProvider())
                                                 .response(aiResponse)
                                                 .aiUser(aiUser)
                                                 .build();
        aiRequestRepository.save(aiRequest);

        return AiRequestDto.PostOutput.builder()
                .userResponse(aiResponse)
                .build();
    }

    public boolean isAuthorizedToPrompt(AiRequestDto.PostInput request) {
        Instant windows = Instant.now().minus(Duration.ofMinutes(limitRateDelay));
        List<AiRequest> listOfRequest = aiRequestRepository.findByUserIdAndCreatedAtAfter(request.userId(), windows);
        return listOfRequest.size() <= amountOfPromptLimit;
    }

}
