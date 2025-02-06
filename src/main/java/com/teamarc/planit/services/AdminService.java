package com.teamarc.planit.services;

import com.teamarc.planit.dto.OnboardOrganiserDTO;
import com.teamarc.planit.entity.OnboardOrganiser;
import com.teamarc.planit.entity.Organiser;
import com.teamarc.planit.entity.User;
import com.teamarc.planit.entity.enums.Role;
import com.teamarc.planit.exceptions.RuntimeConflictException;
import com.teamarc.planit.repository.OnboardOrganiserRepository;
import com.teamarc.planit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Secured("ROLE_ADMIN")
public class AdminService {


    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final OnboardOrganiserRepository onboardOrganiserRepository;
    private final OrganiserService organiserService;

    public void onboardOrganizer(Long userId, OnboardOrganiserDTO onboardOrganiserDTO) {
        User user = userService.getUserById(userId);
        if (user.getRoles().contains(Role.ORGANIZER)) {
            throw new RuntimeConflictException("user with id: " + userId + " is already a employer");
        }

        Organiser organiser = Organiser.builder()
                .user(user)
                .address(onboardOrganiserDTO.getAddress())
                .contact(onboardOrganiserDTO.getContact())
                .description(onboardOrganiserDTO.getDescription())
                .firmName(onboardOrganiserDTO.getFirmName())
                .build();
        user.getRoles().add(Role.ORGANIZER);
        userRepository.save(user);
        organiserService.createNewOrganiser(organiser);
        onboardOrganiserRepository.delete(modelMapper.map(onboardOrganiserDTO, OnboardOrganiser.class));

    }

    public void onboardHost(Long userId, OnboardOrganiserDTO onboardOrganiserDTO) {
        User user = userService.getUserById(userId);
        if (user.getRoles().contains(Role.HOST)) {
            throw new RuntimeConflictException("user with id: " + userId + " is already a employer");
        }

        Organiser organiser = Organiser.builder()
                .user(user)
                .address(onboardOrganiserDTO.getAddress())
                .contact(onboardOrganiserDTO.getContact())
                .description(onboardOrganiserDTO.getDescription())
                .firmName(onboardOrganiserDTO.getFirmName())
                .build();
        user.getRoles().add(Role.HOST);
        userRepository.save(user);
        organiserService.createNewOrganiser(organiser);
        onboardOrganiserRepository.delete(modelMapper.map(onboardOrganiserDTO, OnboardOrganiser.class));
    }

    public void onboardServiceProvider(Long userId, OnboardOrganiserDTO onboardOrganiserDTO) {
        User user = userService.getUserById(userId);
        if (user.getRoles().contains(Role.SERVICE_PROVIDER)) {
            throw new RuntimeConflictException("user with id: " + userId + " is already a employer");
        }

        Organiser organiser = Organiser.builder()
                .user(user)
                .address(onboardOrganiserDTO.getAddress())
                .contact(onboardOrganiserDTO.getContact())
                .description(onboardOrganiserDTO.getDescription())
                .firmName(onboardOrganiserDTO.getFirmName())
                .build();
        user.getRoles().add(Role.SERVICE_PROVIDER);
        userRepository.save(user);
        organiserService.createNewOrganiser(organiser);
        onboardOrganiserRepository.delete(modelMapper.map(onboardOrganiserDTO, OnboardOrganiser.class));
    }

    public Page<OnboardOrganiserDTO> getOrganizerRequests(PageRequest pageRequest) {
        return onboardOrganiserRepository.findAll(pageRequest)
                .map(onboardOrganiser -> modelMapper.map(onboardOrganiser, OnboardOrganiserDTO.class));
    }

    public Page<OnboardOrganiserDTO> getHostRequests(PageRequest pageRequest) {
        return onboardOrganiserRepository.findAll(pageRequest)
                .map(onboardOrganiser -> modelMapper.map(onboardOrganiser, OnboardOrganiserDTO.class));
    }

    public Page<OnboardOrganiserDTO> getServiceProviderRequests(PageRequest pageRequest) {
        return onboardOrganiserRepository.findAll(pageRequest)
                .map(onboardOrganiser -> modelMapper.map(onboardOrganiser, OnboardOrganiserDTO.class));
    }

    public Long getTotalUsers() {
        return userRepository.count();
    }

    public void rejectOrganizer(Long userId) {
        onboardOrganiserRepository.deleteByUserId(userId);
    }

    public void rejectHost(Long userId) {
        onboardOrganiserRepository.deleteByUserId(userId);
    }

    public void rejectServiceProvider(Long userId) {
        onboardOrganiserRepository.deleteByUserId(userId);
    }
}
