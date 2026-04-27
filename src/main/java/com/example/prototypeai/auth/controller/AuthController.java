package com.example.prototypeai.auth.controller;

import com.example.prototypeai.auth.dto.LoginRequestDto;
import com.example.prototypeai.auth.dto.LoginResponseDto;
import com.example.prototypeai.constants.Constants;
import com.example.prototypeai.role.entity.Role;
import com.example.prototypeai.role.repository.RoleRepository;
import com.example.prototypeai.auth.dto.RegisterRequestDto;
import com.example.prototypeai.security.jwtutil.JwtUtil;
import com.example.prototypeai.user.dto.AiUserDto;
import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.user.repository.IAiUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final IAiUserRepository iAiUserRepository;
    private final RoleRepository roleRepository;
    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register/public")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        CompromisedPasswordDecision decision = compromisedPasswordChecker.check(registerRequestDto.motDePasseHash());
        if (decision.isCompromised()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("password", "Veuillez choisir un mot de passe plus sécurisé"));
        }
        Optional<AiUser> user = iAiUserRepository.findAiUserByEmail(registerRequestDto.email());
        if (user.isPresent()) {
            AiUser aiUser = user.get();
            Map<String, Object> errors = new HashMap<>();
            if (aiUser.getEmail().equals(registerRequestDto.email())) {
                errors.put("email", "Cet email existe déjà");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        AiUser aiUser = new AiUser();
        BeanUtils.copyProperties(registerRequestDto, aiUser);
        aiUser.setMotDePasseHash(passwordEncoder.encode(registerRequestDto.motDePasseHash()));
        Role role = roleRepository.findRoleByRoleName(Constants.ROLE_USER).orElseThrow(()
                -> new IllegalArgumentException("Ce rôle n'existe pas : " + Constants.ROLE_USER));
        aiUser.setRole(role);
        iAiUserRepository.save(aiUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Votre compte a bien été créé");
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
