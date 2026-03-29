package com.example.prototypeai.client;

import com.example.prototypeai.enums.AiProvider;

public interface AskAi {

    String sendRequest(String UserRequest);
    AiProvider.RequestType getProvider();
}
