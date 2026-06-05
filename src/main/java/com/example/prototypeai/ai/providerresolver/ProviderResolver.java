package com.example.prototypeai.ai.providerresolver;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.subscription.entity.UserSubscription;
import com.example.prototypeai.user.entity.AiUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProviderResolver {

    private final List<AskAi> askAiList;

    public List<AskAi> getUserAi(AiRequestDto.PostInput request, Authentication authentication) {
        AiUser user = (AiUser) authentication.getPrincipal();
        assert user != null;
        UserSubscription.SubscriptionType subscriptionType = user.getUserSubscription().getSubscriptionType();

        return askAiList.stream().filter(p -> p.support(request.requestType(), user)).toList();
    }

}
