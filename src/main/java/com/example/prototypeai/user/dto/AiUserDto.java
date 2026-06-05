package com.example.prototypeai.user.dto;

import com.example.prototypeai.auth.providertype.AiProviderType;
import com.example.prototypeai.role.roleenum.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter @Setter
@ToString
public class AiUserDto {

    private Long userId;
    private List<String> response;
    private List<AiProviderType> aiProviders;
    private String name;
    private String email;
    private RoleType role;
}
