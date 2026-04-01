package com.example.prototypeai.ai.model;

import com.example.prototypeai.enums.AiProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AiRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String request;
    private String response;

    private LocalDateTime requestHour;

    @Enumerated(EnumType.STRING)
    private AiProvider.RequestType requestType;

}
