package com.example.prototypeai.ai.controller;

import com.example.prototypeai.ai.dto.AskAIRequest;
import com.example.prototypeai.ai.dto.AskAIResponse;
import com.example.prototypeai.ai.service.AskAi;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class AskAiController {

    private final Map<String, AskAi> aiServices;

    public AskAiController(Map<String, AskAi> aiServices) {
        this.aiServices = aiServices;
    }

    @PostMapping("/{aiName}")
    public AskAIResponse postAskAiFromUser(@PathVariable String aiName, @Valid @RequestBody AskAIRequest request) {

        AskAi service = aiServices.get(aiName);

        return AskAIResponse.builder()
                            .userResponse(service.ask(request.getUserRequest()))
                            .build();
    }

}
