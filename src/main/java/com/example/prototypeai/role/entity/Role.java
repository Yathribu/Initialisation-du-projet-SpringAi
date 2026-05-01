package com.example.prototypeai.role.entity;

import com.example.prototypeai.baseentity.BaseEntity;
import com.example.prototypeai.role.roleenum.RoleType;
import com.example.prototypeai.user.entity.AiUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
@Entity
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType roleName;

    List<AiUser> aiUsers;

}
