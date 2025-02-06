package com.teamarc.planit.controller;


import com.teamarc.planit.dto.ParticipantDTO;
import com.teamarc.planit.services.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/participant")
@RequiredArgsConstructor
@Secured("ROLE_PARTICIPANT")
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping()
    public ResponseEntity<Page<ParticipantDTO>> getAllParticipants(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                                   @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize, Sort.by(Sort.Direction.DESC, "appliedDate", "applicationId"));

        return ResponseEntity.ok(participantService.getAllParticipants(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDTO> getParticipantById(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.getParticipantById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ParticipantDTO> updateParticipant(@PathVariable Long id, @RequestBody Map<String, Object> object) {
        return ResponseEntity.ok(participantService.updateParticipant(id, object));
    }

}
