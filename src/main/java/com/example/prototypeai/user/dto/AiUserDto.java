package com.example.prototypeai.user.dto;

import com.example.prototypeai.ai.client.AskAi;
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
    private List<AskAi> aiProviders;
    private String name;
    private String email;
    private RoleType role;
}
