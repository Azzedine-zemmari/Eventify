package com.security.eventify.dto.userDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventCreateDto {

    @NotBlank(message = "Le titre est requis")
    private String title;

    @NotBlank(message = "La description est requise")
    private String description;

    @NotBlank(message = "Le lieu est requis")
    private String location;

    @NotNull(message = "La date et l'heure sont requises")
    @Future(message = "La date doit être dans le futur")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateTime;

    @Min(value = 1, message = "La capacité doit être au moins 1")
    private int capacity;

    @NotNull(message = "L'organisateur est requis")
    private Integer organizerId; // ID du User
}
