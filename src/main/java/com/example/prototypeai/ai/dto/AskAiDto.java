package com.example.prototypeai.ai.dto;

import com.example.prototypeai.util.enums.RequestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
public class AskAiDto {

    @Builder
    public record PostInput(@NotBlank String userRequest, @NotNull RequestType aiProvider, @NotNull Long userId) {

    }

    @Builder
    public record PostOutput(String userResponse) {
    }

}
