package com.example.prototypeai.ai.controller;

import com.example.prototypeai.ai.dto.AiRequestDto;
import com.example.prototypeai.ai.service.AiRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class AiRequestController {

    private final AiRequestService aiServices;

    public AiRequestController(AiRequestService aiServices) {
        this.aiServices = aiServices;
    }

    @PostMapping("/askai")
    public ResponseEntity<AiRequestDto.PostOutput> sendSingle(@Valid @RequestBody AiRequestDto.PostInput request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aiServices.sendSingleRequest(request));
    }

}
