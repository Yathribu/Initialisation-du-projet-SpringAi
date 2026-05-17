package com.example.prototypeai.ai.entity;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.baseentity.BaseEntity;
import com.example.prototypeai.user.entity.AiUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "AiRequest.getAllRequest",
        query = "SELECT NEW com.example.prototypeai.admin.dto.AiRequestAdminDto(r.id, r.request, r.response, r.aiProviders, r.createdAt, u.id, u.email, u.userSubscription) FROM AiRequest r JOIN r.aiUser u")
})
@Entity
public class AiRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String request;
    private List<String> response;

    private List<AskAi> aiProviders;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AiUser aiUser;

}
