package com.security.eventify.service.interfaces;

import com.security.eventify.dto.userDto.RegistrationDto;

public interface RegistrationInterface {
    RegistrationDto registrer(int eventId , String email);
}
