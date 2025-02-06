package com.teamarc.planit.controller;

import com.teamarc.planit.dto.EventDTO;
import com.teamarc.planit.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/event")
public class EventController {

    private final EventService eventService;

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    @GetMapping()
    public ResponseEntity<Page<EventDTO>> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(eventService.getAllEvents(page, size));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO));
    }

    @PutMapping(path = "/update/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody Map<String, Object> object) {
        return ResponseEntity.ok(eventService.updateEvent(id, object));
    }

    @DeleteMapping(path = "/delete/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
