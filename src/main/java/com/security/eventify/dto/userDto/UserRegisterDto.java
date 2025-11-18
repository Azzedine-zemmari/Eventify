package com.security.eventify.dto.userDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// pour inserer un utilisateur
@Data
public class UserRegisterDto {
// id est généré automatique
    @NotBlank(message = "Veuillez saisir le nom !")
    private String name;

    @NotBlank(message = "Veuillez saisir l'email !")
    private String email;

    @NotBlank(message = "Veuillez saisir le mot de passe !")
    private String password;
}
