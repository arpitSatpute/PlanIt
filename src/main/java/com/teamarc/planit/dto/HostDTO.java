package com.teamarc.planit.dto;

import com.teamarc.planit.entity.Event;
import com.teamarc.planit.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
public class HostDTO {


    private Long id;

    private String address;
    private String contact;


    private UserDTO user;


    private List<EventDTO> events;

    // Getters and Setters
}