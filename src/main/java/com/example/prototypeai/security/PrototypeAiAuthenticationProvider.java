package com.example.prototypeai.security;

import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.user.repository.IAiUserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PrototypeAiAuthenticationProvider implements AuthenticationProvider {

    private final IAiUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        AiUser aiUser = userRepository.findAiUserByEmail(username)
                                      .orElseThrow(() -> new UsernameNotFoundException("Le nom d'utilistaeur : " + username + " n'a pas été trouvé"));
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(aiUser.getRole().getRoleName().toString()));
        if(passwordEncoder.matches(password, aiUser.getMotDePasse())) {
            return new UsernamePasswordAuthenticationToken(aiUser, null, authorities);
        } else {
            throw  new BadCredentialsException("Mot de passe incorrect");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
