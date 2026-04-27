package com.example.prototypeai.auth.dto;

import com.example.prototypeai.user.dto.AiUserDto;

public record LoginResponseDto(String message, AiUserDto aiUserDto, String jwtToken) {
}
