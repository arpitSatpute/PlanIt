package com.teamarc.planit.controller;

import com.teamarc.planit.dto.OnboardOrganiserDTO;
import com.teamarc.planit.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/onboard/organizer/{userId}")
    public ResponseEntity<Void> onboardOrganizer(@PathVariable Long userId, @RequestBody OnboardOrganiserDTO onboardOrganiserDTO) {
        adminService.onboardOrganizer(userId,onboardOrganiserDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/onboard/host/{userId}")
    public ResponseEntity<Void> onboardHost(@PathVariable Long userId, @RequestBody OnboardOrganiserDTO onboardOrganiserDTO) {
        adminService.onboardHost(userId,onboardOrganiserDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/onboard/service-provider/{userId}")
    public ResponseEntity<Void> onboardServiceProvider(@PathVariable Long userId, @RequestBody OnboardOrganiserDTO onboardOrganiserDTO) {
        adminService.onboardServiceProvider(userId,onboardOrganiserDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/request/organizers")
    public ResponseEntity<Page<OnboardOrganiserDTO>> getOrganizerRequests(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                                          @RequestParam(defaultValue = "10", required = false) Integer pageSize ) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize);
        return ResponseEntity.ok(adminService.getOrganizerRequests(pageRequest));
    }

    @GetMapping("/request/hosts")
    public ResponseEntity<Page<OnboardOrganiserDTO>> getHostRequests(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                                          @RequestParam(defaultValue = "10", required = false) Integer pageSize ) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize);
        return ResponseEntity.ok(adminService.getHostRequests(pageRequest));
    }

    @GetMapping("/request/service-providers")
    public ResponseEntity<Page<OnboardOrganiserDTO>> getServiceProviderRequests(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                                          @RequestParam(defaultValue = "10", required = false) Integer pageSize ) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize);
        return ResponseEntity.ok(adminService.getServiceProviderRequests(pageRequest));
    }

    @GetMapping(path = "/totalUsers")
    public ResponseEntity<Long> getTotalUsers() {
        return ResponseEntity.ok(adminService.getTotalUsers());
    }

    @PostMapping("/reject/organizer/{userId}")
    public ResponseEntity<Void> rejectOrganizer(@PathVariable Long userId) {
        adminService.rejectOrganizer(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reject/host/{userId}")
    public ResponseEntity<Void> rejectHost(@PathVariable Long userId) {
        adminService.rejectHost(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reject/service-provider/{userId}")
    public ResponseEntity<Void> rejectServiceProvider(@PathVariable Long userId) {
        adminService.rejectServiceProvider(userId);
        return ResponseEntity.ok().build();
    }


}
