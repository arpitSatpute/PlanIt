package com.teamarc.planit.controller;

import com.teamarc.planit.services.HostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/host")
@RequiredArgsConstructor
public class HostController {

    private final HostService hostService;

    @PostMapping(path = "/requestSession/{organiserId}")
    public ResponseEntity<UUID> requestSession(@PathVariable Long organiserId, String date) {
        return ResponseEntity.ok(hostService.requestSession(organiserId, date));


    }

    @GetMapping("/{organiserId}")
    public ResponseEntity<UUID> getSessionId(@PathVariable Long organiserId) {
        return ResponseEntity.ok(hostService.getSessionId(organiserId));
    }

    @DeleteMapping("/{organiserId}")
    public ResponseEntity<String> deleteSession(@PathVariable Long organiserId) {
        return ResponseEntity.ok(hostService.deleteSession(organiserId));
    }


}
