package com.example.prototypeai.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(

        @NotBlank(message = "Le prénom est obligatoire")
        @Size(min = 2, max = 30, message = "Le nom doit être compris entre 2 et 30 caractères")
        String name,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Un email valide est nécessaire")
        String email,

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(min = 8, max = 20, message = "Le mot de passe doit être compris entre 2 et 20 caractères")
        String motDePasseHash,

        @NotBlank(message = "Le numéro de téléphone est obligatoire")
        @Pattern(regexp = "^0[67]\\\\d{8}$", message = "Le numéro de téléphone doit contenir 10 chiffres")
        String numeroDeTelephone

) {
}
