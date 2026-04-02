package com.example.prototypeai.ai.dto;

import com.example.prototypeai.util.enums.AiProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
public class AskAiDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostInput {
        @NotBlank
        private String userRequest;

        @NotNull
        private AiProvider.RequestType aiProvider;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostOutput {
        private String userResponse;
    }

}
