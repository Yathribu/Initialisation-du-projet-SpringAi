package com.example.prototypeai.client;

import com.example.prototypeai.util.enums.AiProvider;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenAi implements AskAi{

    private final OpenAIClient client;

    public OpenAi(@Value("${openai.api.key}") String apiKey) {
        this.client = OpenAIOkHttpClient.builder()
                                        .apiKey(apiKey)
                                        .build();
    }

    @Override
    public String sendRequest(String UserRequest) {
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                                                                      .model("gpt-4o-mini")
                                                                      .addUserMessage(UserRequest)
                                                                      .build();

        ChatCompletion response = client.chat().completions().create(params);

        return response.choices()
                       .get(0)
                       .message()
                       .content()
                       .orElseThrow();
    }

    @Override
    public AiProvider.RequestType getProvider() {
        return AiProvider.RequestType.OPEN_AI;
    }

}
