package com.teamarc.planit.controller;

import com.teamarc.planit.dto.VenueDTO;
import com.teamarc.planit.services.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venue")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping("/create")
    public void createVenue(@RequestBody VenueDTO venueDTO) {
        venueService.createVenue(venueDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueDTO> getVenueById(@RequestParam Long id) {
        return ResponseEntity.ok(venueService.getVenueById(id));
    }

    @GetMapping
    public ResponseEntity<List<VenueDTO>> getAllVenue() {
        return ResponseEntity.ok(venueService.getAllVenue());
    }

}
