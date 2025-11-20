package com.security.eventify.repository;

import com.security.eventify.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration , Integer> {
}
