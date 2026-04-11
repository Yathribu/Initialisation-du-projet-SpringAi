package com.example.prototypeai.ai.entity;

import com.example.prototypeai.baseentity.BaseEntity;
import com.example.prototypeai.user.entity.User;
import com.example.prototypeai.util.enums.RequestType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id",  nullable = false)
    private User user;

}
