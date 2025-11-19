package com.security.eventify.dto.userDto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegistrationDto {
    private int id;
    private int userId;
    private int eventId;
    private String eventTitle;
    private LocalDateTime registeredAt;
    private String status;
}