package com.security.eventify.service.interfaces;

import com.security.eventify.dto.userDto.RegistrationDto;

import java.util.List;

public interface RegistrationInterface {
    RegistrationDto registrer(int eventId , String email);
    List<RegistrationDto> getAllRegistration(String email);
}
