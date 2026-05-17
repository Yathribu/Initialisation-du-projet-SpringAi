package com.example.prototypeai.ai.client;

import com.example.prototypeai.subscription.entity.UserSubscription;
import com.example.prototypeai.util.enums.RequestType;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GrokPowerfull implements AskAi{

    private final OpenAIClient client;

    public GrokPowerfull(@Value("${xai.api.key}") String apiKey) {
        this.client = OpenAIOkHttpClient.builder()
                                        .baseUrl("https://api.x.ai/v1")
                                        .apiKey(apiKey)
                                        .build();
    }

    @Override
    public String sendRequest(String UserRequest) {

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
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

    @Override
    public RequestType getProvider() {
        return RequestType.GROK_POWERFULL;
    }

    @Override
    public boolean support(UserSubscription.SubscriptionType subscriptionType) {
        return subscriptionType == UserSubscription.SubscriptionType.PREMIUM;
    }


}
