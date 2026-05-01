package com.example.prototypeai.user.dto;

import com.example.prototypeai.role.roleenum.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class AiUserDto {

    private String message;
    private Long userId;
    private String name;
    private String email;
    private String mobileNumber;
    private RoleType role;
}
