package com.example.prototypeai.user.entity;

import com.example.prototypeai.ai.entity.AiRequest;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AiRequest> aiRequests;
}
