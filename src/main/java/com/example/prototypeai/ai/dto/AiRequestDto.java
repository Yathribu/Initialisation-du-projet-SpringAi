package com.example.prototypeai.ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Builder
public class AiRequestDto {

    @Builder
    public record PostInput(@NotBlank String userRequest, @NotNull Long userId) {

    }

    @Builder
    public record PostOutput(List<String> userResponse) {
    }

}
