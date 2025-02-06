package com.teamarc.planit.controller;

import com.teamarc.planit.dto.OnboardHostDTO;
import com.teamarc.planit.dto.OnboardOrganiserDTO;
import com.teamarc.planit.dto.OnboardServiceProviderDTO;
import com.teamarc.planit.entity.User;
import com.teamarc.planit.services.AdminService;
import com.teamarc.planit.services.ParticipantService;
import com.teamarc.planit.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final HostService hostService;
    private final ParticipantService participantService;
    private final AdminService adminService;
    private final UserService userService;

    @PostMapping("/request/participant")
    public ResponseEntity<Void> requestParticipant(@RequestBody User user) {
        participantService.createParticipant(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/host")
    public ResponseEntity<Void> requestHost(OnboardHostDTO user) {
        userService.createHost(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/serviceProvider")
    public ResponseEntity<Void> requestServiceProvider(OnboardServiceProviderDTO user) {
        userService.createServiceProvider(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/orgainzer")
    public ResponseEntity<Void> requestOrganizer(OnboardOrganiserDTO user) {
        userService.createOrganizer(user);
        return ResponseEntity.ok().build();
    }

}
