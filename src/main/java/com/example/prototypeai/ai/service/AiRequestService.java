package com.example.prototypeai.ai.service;

import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.entity.AiRequest;
import com.example.prototypeai.ai.orchestrateur.AiOrchestror;
import com.example.prototypeai.ratelimit.RateLimit;
import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.user.repository.IAiUserRepository;
import com.example.prototypeai.ai.repository.AiRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // Pour toutes les futurs rest api read
public class AiRequestService {

    private final AiRequestRepository aiRequestRepository;
    private final IAiUserRepository userRepository;
    private final AiOrchestror orchestrator;
    private final RateLimit rateLimit;

    @Transactional // je reste sur les param par défaut fourni par spring.
    public AiRequestDto.PostOutput sendSingleRequest(AiRequestDto.PostInput request) {

        // On veut que l'utilisateur existe avant de faire une request.
        AiUser aiUser = userRepository.findById(request.userId())
                                      .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        // A-t-il dépassé la limite de 15 requêtes par 10 minutes ?
        if(!rateLimit.isAuthorizedToPrompt(request)) {
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

}
