package com.security.eventify.mapper;

import com.security.eventify.dto.userDto.RegistrationDto;
import com.security.eventify.model.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "eventTitle", source = "event.title")
    @Mapping(target = "status", source = "status")
    RegistrationDto registerToRegisterDto(Registration registration);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "event.id", source = "eventId")
    @Mapping(target = "status", source = "status")
    Registration registerDtoToRegister(RegistrationDto registrationDto);
}
