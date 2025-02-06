package com.teamarc.planit.dto;

import com.teamarc.planit.entity.Event;
import com.teamarc.planit.entity.Ticket;
import com.teamarc.planit.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
public class ParticipantDTO {


    private Long id;

    private String address;
    private String contact;

    private UserDTO user;

    private List<EventDTO> events;

    private List<TicketDTO> tickets;

    // Getters and Setters
}