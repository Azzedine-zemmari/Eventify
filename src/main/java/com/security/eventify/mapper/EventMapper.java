package com.security.eventify.mapper;

import com.security.eventify.dto.userDto.EventCreateDto;
import com.security.eventify.dto.userDto.EventDto;
import com.security.eventify.dto.userDto.EventUpdateDto;
import com.security.eventify.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {

    EventDto eventToEventDto(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Event eventCreateDtoToEvent(EventCreateDto eventCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEventFromDto(EventUpdateDto eventUpdateDto, @MappingTarget Event event);
}