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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Relation vers User

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;  // Relation vers Event
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