package com.teamarc.planit.entity;

import com.teamarc.planit.entity.enums.SessionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrganiser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private UUID sessionId;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;
    @ManyToOne
    @JoinColumn(name = "organiser_id")
    private Organiser organiser;

}
