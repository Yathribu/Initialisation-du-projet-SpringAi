package com.example.prototypeai.ai.controller;

import com.example.prototypeai.ai.dto.AskAiDto;
import com.example.prototypeai.ai.service.AskAiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class AskAiController {

    private final AskAiService aiServices;

    public AskAiController(AskAiService aiServices) {
        this.aiServices = aiServices;
    }

    @PostMapping()
    public AskAiDto.PostOutput postAskAiFromUser(@Valid @RequestBody AskAiDto.PostInput request) {

        return aiServices.sendRequest(request.getUserRequest(), request.getAiProvider());
    }

}
