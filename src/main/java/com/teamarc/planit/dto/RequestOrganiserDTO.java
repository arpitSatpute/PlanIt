package com.teamarc.planit.dto;

import com.teamarc.planit.entity.Organiser;
import com.teamarc.planit.entity.enums.SessionStatus;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestOrganiserDTO {


    private Long id;
    private String date;
    private UUID sessionId;
    private SessionStatus status;

    private Organiser organiser;

}
