package com.security.eventify.controller;

import com.security.eventify.dto.userDto.EventCreateDto;
import com.security.eventify.dto.userDto.EventDto;
import com.security.eventify.dto.userDto.EventUpdateDto;
import com.security.eventify.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {

    private final EventService eventService;

    public OrganizerController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public ResponseEntity<EventDto> createEvent(
            @Valid @RequestBody EventCreateDto eventCreateDto,
            Authentication authentication) {
        String email = authentication.getName();
        EventDto eventDto = eventService.createEvent(eventCreateDto, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventDto);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<EventDto> updateEvent(
            @PathVariable int id,
            @Valid @RequestBody EventUpdateDto eventUpdateDto,
            Authentication authentication) {
        String email = authentication.getName();
        EventDto eventDto = eventService.updateEvent(id, eventUpdateDto, email);
        return ResponseEntity.ok(eventDto);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable int id,
            Authentication authentication) {
        String email = authentication.getName();
        eventService.deleteEventByOrganizer(id, email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> getOrganizerEvents(Authentication authentication) {
        String email = authentication.getName();
        List<EventDto> events = eventService.getEventsByOrganizer(email);
        return ResponseEntity.ok(events);
    }
}