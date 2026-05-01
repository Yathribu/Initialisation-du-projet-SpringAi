package com.example.prototypeai.auth.service;

import com.example.prototypeai.auth.dto.RegisterRequestDto;
import com.example.prototypeai.auth.mapper.AiUserMapper;
import com.example.prototypeai.constants.Constants;
import com.example.prototypeai.role.entity.Role;
import com.example.prototypeai.role.repository.RoleRepository;
import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.user.repository.IAiUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final IAiUserRepository iAiUserRepository;
    private final RoleRepository roleRepository;
    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final PasswordEncoder passwordEncoder;
    private final AiUserMapper aiUserMapper;

    public ResponseEntity<?> registerUser(RegisterRequestDto registerRequestDto) {

        CompromisedPasswordDecision decision = compromisedPasswordChecker.check(registerRequestDto.motDePasse());
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

        AiUser aiUser = aiUserMapper.toEntity(registerRequestDto);
        aiUser.setMotDePasseHash(passwordEncoder.encode(registerRequestDto.motDePasse()));
        Role role = roleRepository.findRoleByRoleName(Constants.ROLE_USER).orElseThrow(()
                -> new IllegalArgumentException("Ce rôle n'existe pas : " + Constants.ROLE_USER));
        aiUser.setRole(role);
        iAiUserRepository.save(aiUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("Votre compte a bien été créé");
    }

}
