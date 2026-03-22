package com.example.prototypeai.ai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AskAIRequest {

    @NotBlank
    private String userRequest;

}
