package com.example.prototypeai.admin.dto;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.subscription.entity.UserSubscription;

import java.time.Instant;
import java.util.List;

public record AiRequestAdminDto(Long id, String request,
                                List<String> response, List<AskAi> aiProviders,
                                Instant createdAt, Long userId,
                                String email, UserSubscription userSubscription) {
}
