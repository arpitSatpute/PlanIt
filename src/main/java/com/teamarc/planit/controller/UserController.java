package com.teamarc.planit.controller;

import com.teamarc.planit.dto.OnboardHostDTO;
import com.teamarc.planit.dto.OnboardOrganiserDTO;
import com.teamarc.planit.dto.OnboardServiceProviderDTO;
import com.teamarc.planit.entity.User;
import com.teamarc.planit.services.ParticipantService;
import com.teamarc.planit.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final ParticipantService participantService;
    private final UserService userService;

    @PostMapping("/request/participant/{userId}")
    public ResponseEntity<Void> requestParticipant(@RequestBody User user, @PathVariable Long userId) {
        participantService.createParticipant(user, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/host/{userId}")
    public ResponseEntity<Void> requestHost(OnboardHostDTO user, @PathVariable Long userId) {
        userService.createHost(user, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/serviceProvider/{userId}")
    public ResponseEntity<Void> requestServiceProvider(OnboardServiceProviderDTO user, @PathVariable Long userId) {
        userService.createServiceProvider(user, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/orgainzer/{userId}")
    public ResponseEntity<Void> requestOrganizer(OnboardOrganiserDTO user, @PathVariable Long userId) {
        userService.createOrganizer(user, userId);
        return ResponseEntity.ok().build();
    }

}
