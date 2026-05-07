package com.example.prototypeai.userrole;

import com.example.prototypeai.baseentity.BaseEntity;
import com.example.prototypeai.role.roleenum.RoleType;
import com.example.prototypeai.util.enums.RequestType;
import jakarta.persistence.*;

@Entity
public class UserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private RoleType role;

    private Long entityId;

    @Enumerated(EnumType.ORDINAL)
    private RequestType requestType;

}
