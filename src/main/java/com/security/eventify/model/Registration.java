package com.security.eventify.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private int eventId;
    private LocalDateTime registeredAt;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @PrePersist
    protected void onCreate() {
        registeredAt = LocalDateTime.now();
        if (status == null) {
            status = RegistrationStatus.CONFIRMED;
        }
    }
}