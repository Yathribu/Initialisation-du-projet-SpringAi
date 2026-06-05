package com.example.prototypeai.ai.service;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.entity.AiRequest;
import com.example.prototypeai.ai.providerresolver.ProviderResolver;
import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.user.repository.IAiUserRepository;
import com.example.prototypeai.ai.repository.AiRequestRepository;
import com.ratelimiterspringcore.ratelimit.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AiRequestService {

    private final AiRequestRepository aiRequestRepository;
    private final IAiUserRepository userRepository;
    private final RateLimiter rateLimiter;
    private final ProviderResolver providerResolver;
    private final RequestToAi requestToAi;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Transactional
    public AiRequestDto.PostOutput sendPrompt(AiRequestDto.PostInput request, Authentication authentication) {

        String username = authentication.getName();
        AiUser user = userRepository.findAiUserByEmail(username)
                                    .orElseThrow(() -> new RuntimeException("User not found"));

        // A-t-il dépassé la limite de 15 requêtes par 10 minutes ?
        if(!rateLimiter.isAuthorizedToPrompt(request.userId())) {
            throw new IllegalArgumentException("Nombre de requête limité");
        }

        List<AskAi> aiProviders = providerResolver.getUserAi(request, authentication);

        List<String> aiResponse = requestToAi.getAiResponse(request, aiProviders);

        AiRequest aiRequest = AiRequest.builder().request(request.userRequest())
                                                 .aiProviders(aiProviders)
                                                 .response(aiResponse)
                                                 .aiUser(user)
                                                 .build();
        aiRequestRepository.save(aiRequest);

        return AiRequestDto.PostOutput.builder()
                .userResponse(aiResponse)
                .build();
    }

}
