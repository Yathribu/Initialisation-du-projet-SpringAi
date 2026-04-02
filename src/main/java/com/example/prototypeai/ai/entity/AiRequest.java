package com.example.prototypeai.ai.entity;

import com.example.prototypeai.util.enums.AiProvider;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AiRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String request;
    private String response;

    @CreatedDate
    private LocalDateTime requestCreatedAt;

    @Enumerated(EnumType.STRING)
    private AiProvider.RequestType requestType;

}
