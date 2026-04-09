package com.example.prototypeai.ai.client;

import com.example.prototypeai.util.enums.RequestType;

public interface AskAi {
    String sendRequest(String UserRequest);

    RequestType getProvider();
}
