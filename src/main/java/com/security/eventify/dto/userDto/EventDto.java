package com.security.eventify.dto.userDto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventDto {
    private int id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private int capacity;
    private int organizerId;
    private LocalDateTime createdAt;
}