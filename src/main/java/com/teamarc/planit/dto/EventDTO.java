package com.teamarc.planit.dto;

import com.teamarc.planit.entity.*;
import com.teamarc.planit.entity.enums.Mode;
import com.teamarc.planit.entity.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
public class EventDTO {

    private Long id;
    private String name;
    private String date;

    private Set<Mode> mode;

    private Venue venue;

    private Integer capacity;
    private String description;

    private Set<Status> status;

    private List<SlotDTO> schedule;
    private BigDecimal ticketPrice;

    private TicketDTO ticket;

    private HostDTO host;
    private OrganiserDTO organizer;

    private List<ParticipantDTO> participants;

    private List<ServiceProviderDTO> serviceProviders;


}
