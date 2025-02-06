package com.teamarc.planit.dto;


import com.teamarc.planit.entity.ServiceProvider;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
public class RatingDTO {


    private Long id;

    private Double ratingValue;

    private String comment;

    private ServiceProviderDTO serviceProvider;

}
