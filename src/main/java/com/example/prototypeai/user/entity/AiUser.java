package com.example.prototypeai.user.entity;

import com.example.prototypeai.ai.entity.AiRequest;
import com.example.prototypeai.baseentity.BaseEntity;
import com.example.prototypeai.role.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class AiUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
    @Pattern(regexp = "^0[1-9]\\d{8}$", message = "Le numéro de téléphone doit contenir 10 chiffres et commencer par 0")
    private String numeroDeTelephone;

    @OneToMany(mappedBy = "aiUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<AiRequest> aiRequests;

    @Size(max = 200)
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8)
    private String motDePasse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

}
