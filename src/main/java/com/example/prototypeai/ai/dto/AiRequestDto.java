package com.example.prototypeai.ai.dto;

import com.example.prototypeai.ai.mod.Mod;
import com.example.prototypeai.util.enums.RequestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Builder
public class AiRequestDto {

    @Builder
    public record PostInput(@NotBlank String userRequest, @NotNull List<RequestType> aiProvider, @NotNull Long userId, @NotNull Mod mod) {

    }

    @Builder
    public record PostOutput(List<String> userResponse) {
    }

}
