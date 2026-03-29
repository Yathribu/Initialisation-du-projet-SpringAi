package com.example.prototypeai.ai.service;

import com.example.prototypeai.client.AskAi;
import com.example.prototypeai.enums.AiProvider;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AskAiService {

    List<AskAi> aiList;

    public AskAiService(List<AskAi> aiList) {
        this.aiList = aiList;
    }

    public String sendRequest(String request, AiProvider.RequestType requestType) {

        for (AskAi askAi : aiList) {
            if(requestType == askAi.getProvider()){
                return askAi.sendRequest(request);
            }
        }

        throw new RuntimeException("No such AskAi");
    }

}
