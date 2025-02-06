package com.teamarc.planit.entity;

import com.teamarc.planit.entity.enums.Mode;
import com.teamarc.planit.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Mode> mode;

    @OneToOne
    private Venue venue;

    private Integer capacity;
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Status> status;

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<Slot> schedule;
    private BigDecimal ticketPrice;

    @OneToOne(mappedBy = "event")
    @JoinColumn(name = "event_id")
    private Ticket ticket;

    @OneToOne
    private Host host;

    @OneToOne
    private Organiser organizer;

    @OneToMany
    private List<Participant> participants;

    @OneToMany
    private List<ServiceProvider> serviceProviders;

}