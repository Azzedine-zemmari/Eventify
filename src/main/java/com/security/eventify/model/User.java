package com.security.eventify.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    @Column(unique = true)
    private String email ;
    private String password ;
    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Registration> registrations;
}
