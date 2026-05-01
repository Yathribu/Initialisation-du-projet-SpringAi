package com.example.prototypeai.userrole;

import com.example.prototypeai.baseentity.BaseEntity;
import com.example.prototypeai.role.entity.Role;
import com.example.prototypeai.user.entity.AiUser;
import jakarta.persistence.*;

@Entity
public class UserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private AiUser aiUser;

}
