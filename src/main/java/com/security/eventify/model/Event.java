package com.security.eventify.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String location;

    private LocalDateTime dateTime;

    private int capacity;

    @ManyToOne()
    @JoinColumn(name = "organizerId" , nullable = false)
    private User organizer; // ID de l'utilisateur qui a créé l'événement

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Registration> registrations;
}