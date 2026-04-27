package com.example.prototypeai.ai.entity;

import com.example.prototypeai.baseentity.BaseEntity;
import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.util.enums.RequestType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;

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
    private List<String> response;

    @Enumerated(EnumType.STRING)
    private List<RequestType> requestType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AiUser aiUser;

}
