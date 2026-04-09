package com.example.prototypeai.ai.entity;

import com.example.prototypeai.baseentity.BaseEntity;
import com.example.prototypeai.user.entity.User;
import com.example.prototypeai.util.enums.RequestType;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AiRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String request;
    private String response;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
