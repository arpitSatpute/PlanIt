package com.teamarc.planit.dto;

import com.teamarc.planit.entity.enums.Type;
import lombok.Data;

@Data
public class OnboardServiceProviderDTO {

    private Long userId;
    private Type serviceType;
    private String description;
    private String address;
    private String contact;
}
