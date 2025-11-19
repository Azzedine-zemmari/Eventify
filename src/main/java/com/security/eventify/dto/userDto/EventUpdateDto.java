package com.security.eventify.dto.userDto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventUpdateDto {
    private String title;
    private String description;
    private String location;

    @Future(message = "La date doit être dans le futur")
    private LocalDateTime dateTime;

    @Min(value = 1, message = "La capacité doit être au moins 1")
    private Integer capacity;
}