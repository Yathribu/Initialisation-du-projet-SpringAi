package com.example.prototypeai.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AskAIResponse {

    private String userResponse;
}
