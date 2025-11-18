package com.security.eventify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private int id;
    @NotBlank(message = "Veuillez saisir le nom !")
    private String name ;
    @NotBlank(message = "Veuillez saisir l'email !")
    private String email ;
}
