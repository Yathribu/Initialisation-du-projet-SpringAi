package com.example.prototypeai.user.dto;

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
