package com.example.prototypeai.ai.controller;

import com.example.prototypeai.ai.dto.AskAiDto;
import com.example.prototypeai.ai.service.AskAiService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class AskAiController {

    private final AskAiService aiServices;

    public AskAiController(AskAiService aiServices) {
        this.aiServices = aiServices;
    }

    @PostMapping()
    public ResponseEntity<AskAiDto.PostOutput> postAskAiFromUser(@Valid @RequestBody AskAiDto.PostInput request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aiServices.sendRequest(request));
    }

}
