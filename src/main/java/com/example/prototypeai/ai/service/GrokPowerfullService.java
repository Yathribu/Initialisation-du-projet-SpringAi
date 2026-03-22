package com.example.prototypeai.ai.service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("GrokPowerfull")
public class GrokPowerfullService implements AskAi {

    private final OpenAIClient client;

    public GrokPowerfullService(@Value("${xai.api.key}") String apiKey) {
        this.client = OpenAIOkHttpClient.builder()
                                        .baseUrl("https://api.x.ai/v1")
                                        .apiKey(apiKey)
                                        .build();
    }

    public String ask(String UserRequest) {

        ChatCompletionCreateParams params =
                ChatCompletionCreateParams.builder()
                                          .model("grok-3")
                                          .addUserMessage(UserRequest)
                                          .build();

        ChatCompletion response = client.chat().completions().create(params);

        return response.choices()
                       .get(0)
                       .message()
                       .content()
                       .orElseThrow();
    }
}
