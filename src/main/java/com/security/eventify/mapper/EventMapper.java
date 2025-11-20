package com.security.eventify.mapper;

import com.security.eventify.dto.userDto.EventCreateDto;
import com.security.eventify.dto.userDto.EventDto;
import com.security.eventify.dto.userDto.EventUpdateDto;
import com.security.eventify.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EventMapper {

    // Event → EventDto
    @Mapping(target = "organizerId", source = "organizer.id")
    EventDto eventToEventDto(Event event);

    // EventCreateDto → Event
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Event eventCreateDtoToEvent(EventCreateDto eventCreateDto);

    // Update from DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEventFromDto(EventUpdateDto eventUpdateDto, @MappingTarget Event event);
}
