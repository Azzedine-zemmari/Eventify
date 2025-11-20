package com.security.eventify.service;

import com.security.eventify.dto.userDto.RegistrationDto;
import com.security.eventify.exception.EventNotFoundException;
import com.security.eventify.exception.UserNotFound;
import com.security.eventify.mapper.RegistrationMapper;
import com.security.eventify.model.Event;
import com.security.eventify.model.Registration;
import com.security.eventify.model.RegistrationStatus;
import com.security.eventify.model.User;
import com.security.eventify.repository.EventRepository;
import com.security.eventify.repository.RegistrationRepository;
import com.security.eventify.repository.UserRepository;
import com.security.eventify.service.interfaces.RegistrationInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegistrationService implements RegistrationInterface {
    private final RegistrationMapper registrationMapper;
    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public RegistrationService(RegistrationMapper registrationMapper, RegistrationRepository registrationRepository  , EventRepository eventRepository , UserRepository userRepository) {
        this.registrationMapper = registrationMapper;
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RegistrationDto registrer(int eventId , String email){
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()){
            Optional<User> user  = userRepository.findByEmail(email);
            if(!user.isPresent()){
                throw new UserNotFound("utilisateur n est pas existe");
            }
            Registration registration = new Registration();
            registration.setEvent(event.get());
            registration.setStatus(RegistrationStatus.CONFIRMED);
            registration.setUser(user.get());
            registration.setRegisteredAt(LocalDateTime.now());

            registrationRepository.save(registration);

            RegistrationDto registrationDto1 = registrationMapper.registerToRegisterDto(registration);
            return registrationDto1;
        }
        else{
            throw new EventNotFoundException("event n est pas existe");
        }
    }
}
