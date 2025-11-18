package com.security.eventify.dto.userDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// pour afficher les donnes d'utilisateur
@Data
public class UserDto {
    private int id;
    @NotBlank(message = "Veuillez saisir le nom !")
    private String name ;
    @NotBlank(message = "Veuillez saisir l'email !")
    private String email ;
}
