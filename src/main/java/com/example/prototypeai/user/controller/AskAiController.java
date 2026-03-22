package com.example.prototypeai.user.controller;

import com.example.prototypeai.user.dto.AskAIRequest;
import com.example.prototypeai.user.dto.AskAIResponse;
import com.example.prototypeai.user.service.AskAi;
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
