package com.teamarc.planit.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
public class SlotDTO {


    private Long id;
    private String description;
    private String dateTime;

}
