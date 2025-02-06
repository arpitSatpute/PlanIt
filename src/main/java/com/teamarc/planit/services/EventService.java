package com.teamarc.planit.services;

import com.teamarc.planit.dto.EventDTO;
import com.teamarc.planit.dto.TicketDTO;
import com.teamarc.planit.entity.Event;
import com.teamarc.planit.entity.Participant;
import com.teamarc.planit.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventService {


    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final ParticipantService participantService;
    private final EventBookingService eventBookingService;

    public EventDTO getEventById(Long eventId) {
        return modelMapper.map(eventRepository.findById(eventId), EventDTO.class);
    }

    public Page<EventDTO> getAllEvents(int page, int size) {
        return eventRepository.findAll(PageRequest.of(page, size)).map(event -> modelMapper.map(event, EventDTO.class));
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        return modelMapper.map(eventRepository.save(modelMapper.map(eventDTO, Event.class)), EventDTO.class);
    }


    public EventDTO updateEvent(Long id, Map<String, Object> updates) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(Participant.class, field);
            if (fieldToBeUpdated != null) {
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, event, value);
            }
        });
        return modelMapper.map(eventRepository.save(event), EventDTO.class);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public TicketDTO bookEvent(Long eventId) {
        return eventBookingService.bookEvent(eventId, participantService.getCurrentParticipant());
    }

}
