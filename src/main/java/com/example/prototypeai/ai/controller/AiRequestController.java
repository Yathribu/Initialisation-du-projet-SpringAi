package com.example.prototypeai.ai.controller;

import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.service.AiRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class AiRequestController {

    private final AiRequestService aiServices;

    @PostMapping("/askai")
    public ResponseEntity<AiRequestDto.PostOutput> sendPrompt(@Valid @RequestBody AiRequestDto.PostInput request, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aiServices.sendPrompt(request, authentication));
    }

}