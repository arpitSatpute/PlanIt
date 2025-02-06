package com.teamarc.planit.dto;

import com.teamarc.planit.entity.ServiceProvider;
import jakarta.persistence.*;
import lombok.*;

@Data
public class VenueDTO {


    private Long id;
    private String location;
    private Boolean isAvailable;
    private Integer capacity;

    private ServiceProviderDTO owner;

    // Getters and Setters
}