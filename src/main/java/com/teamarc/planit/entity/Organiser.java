package com.teamarc.planit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Organiser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firmName;
    private String description;
    private String address;
    private String contact;

    @OneToOne
    private User user;

    @OneToMany
    private List<Event> events;

}