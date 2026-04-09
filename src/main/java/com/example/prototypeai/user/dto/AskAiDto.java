package com.example.prototypeai.user.dto;

import com.example.prototypeai.util.enums.RequestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class AskAiDto {

    @Builder
    public record PostInput(@NotBlank String userRequest, @NotNull RequestType aiProvider) {}

    @Builder
    public record PostOutput (String userResponse){}

}
