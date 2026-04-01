package com.example.prototypeai.client;

import com.example.prototypeai.enums.AiProvider;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GrokFast implements AskAi{

    private final OpenAIClient client;

    public GrokFast(@Value("${xai.api.key}") String apiKey) {
        System.out.println("API KEY = " + apiKey);
        this.client = OpenAIOkHttpClient.builder()
                                        .baseUrl("https://api.x.ai/v1")
                                        .apiKey(apiKey)
                                        .build();
    }

    @Override
    public String sendRequest(String UserRequest) {
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                                                                      .model("grok-3-fast")
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
        return AiProvider.RequestType.GROK_FAST;
    }

}
