package com.teamarc.planit.dto;

import com.teamarc.planit.entity.Event;
import com.teamarc.planit.entity.Participant;
import com.teamarc.planit.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
public class TicketDTO {


    private Long id;


    private EventDTO event;


    private ParticipantDTO participant;

    private BigDecimal ticketPrice;
    private String qrCode;

    // Getters and Setters
}