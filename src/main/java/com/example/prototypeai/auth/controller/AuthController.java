package com.example.prototypeai.auth.controller;

import com.example.prototypeai.auth.dto.LoginRequestDto;
import com.example.prototypeai.auth.dto.LoginResponseDto;
import com.example.prototypeai.auth.service.RegisterService;
import com.example.prototypeai.auth.dto.RegisterRequestDto;
import com.example.prototypeai.security.jwtutil.JwtUtil;
import com.example.prototypeai.user.dto.AiUserDto;
import com.example.prototypeai.user.entity.AiUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RegisterService  registerService;

    @PostMapping("/register/public")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        return registerService.registerUser(registerRequestDto);
    }

    @PostMapping("/login/public")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            var resultOfAuthentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password()));
            String jwtToken = jwtUtil.generateJwtToken(resultOfAuthentication);
            AiUserDto userDto = new AiUserDto();
            AiUser aiUser = (AiUser) resultOfAuthentication.getPrincipal();
            BeanUtils.copyProperties(aiUser, userDto);
            userDto.setUserId(aiUser.getId());
            userDto.setRole(aiUser.getRole().getRoleName());
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), userDto, jwtToken));
        } catch (BadCredentialsException e) {
            return errorResponse(HttpStatus.UNAUTHORIZED, "Nom d'utilisateur ou mot de passe incorrect");
        } catch (AuthenticationException e) {
            return errorResponse(HttpStatus.UNAUTHORIZED, "Erreur authentication");
        } catch (Exception e) {
            return errorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

    }

    private ResponseEntity<LoginResponseDto> errorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }

}
