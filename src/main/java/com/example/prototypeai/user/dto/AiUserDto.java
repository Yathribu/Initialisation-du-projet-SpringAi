package com.example.prototypeai.user.dto;

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
    private String role;
}
