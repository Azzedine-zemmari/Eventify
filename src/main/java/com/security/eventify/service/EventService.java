package com.security.eventify.service;

import com.security.eventify.dto.userDto.EventCreateDto;
import com.security.eventify.dto.userDto.EventDto;
import com.security.eventify.dto.userDto.EventUpdateDto;
import com.security.eventify.exception.EventNotFoundException;
import com.security.eventify.exception.UnauthorizedActionException;
import com.security.eventify.mapper.EventMapper;
import com.security.eventify.model.Event;
import com.security.eventify.model.User;
import com.security.eventify.repository.EventRepository;
import com.security.eventify.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, UserRepository userRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::eventToEventDto)
                .collect(Collectors.toList());
    }

    public EventDto getEventById(int id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Événement non trouvé avec l'ID: " + id));
        return eventMapper.eventToEventDto(event);
    }

    public EventDto createEvent(EventCreateDto eventCreateDto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Event event = eventMapper.eventCreateDtoToEvent(eventCreateDto);
        event.setOrganizer(user);

        Event savedEvent = eventRepository.save(event);
        return eventMapper.eventToEventDto(savedEvent);
    }

    public EventDto updateEvent(int eventId, EventUpdateDto eventUpdateDto, String email) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Événement non trouvé avec l'ID: " + eventId));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (event.getOrganizer().getId() != user.getId()) {
            throw new UnauthorizedActionException("Vous n'êtes pas autorisé à modifier cet événement");
        }

        eventMapper.updateEventFromDto(eventUpdateDto, event);
        Event updatedEvent = eventRepository.save(event);
        return eventMapper.eventToEventDto(updatedEvent);
    }

    public void deleteEventByOrganizer(int eventId, String email) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Événement non trouvé avec l'ID: " + eventId));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (event.getOrganizer().getId() != user.getId()) {
            throw new UnauthorizedActionException("Vous n'êtes pas autorisé à supprimer cet événement");
        }

        eventRepository.deleteById(eventId);
    }

    public void deleteEventByAdmin(int eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new EventNotFoundException("Événement non trouvé avec l'ID: " + eventId);
        }
        eventRepository.deleteById(eventId);
    }

    public List<EventDto> getEventsByOrganizer(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return eventRepository.findByOrganizerId(user.getId()).stream()
                .map(eventMapper::eventToEventDto)
                .collect(Collectors.toList());
    }
}