package com.teamarc.planit.dto;

import com.teamarc.planit.entity.Event;
import com.teamarc.planit.entity.Rating;
import com.teamarc.planit.entity.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
public class ServiceProviderDTO {


    private Long id;


    private Set<Type> type;

    private String description;
    private String address;
    private String contact;


    private List<RatingDTO> ratings;


    private List<EventDTO> events;

    // Getters and Setters
}