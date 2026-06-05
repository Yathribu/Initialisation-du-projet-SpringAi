package com.example.prototypeai.ai.client;

import com.example.prototypeai.subscription.entity.UserSubscription;
import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.util.enums.RequestType;

public interface AskAi {
    String sendRequest(String UserRequest);

    RequestType getProvider();

    boolean support(RequestType requestType, AiUser aiUser);
}
