package com.example.prototypeai.ratelimit.controller;

import com.example.prototypeai.ratelimit.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RateLimitController {

    private final RateLimiter rateLimiter;

    @GetMapping("/users/{id}/islimitedornot")
    public ResponseEntity<Boolean> isAuthorizedToPrompt(@PathVariable Long id) {
        return ResponseEntity.ok().body(rateLimiter.isAuthorizedToPrompt(id));
    }

}
