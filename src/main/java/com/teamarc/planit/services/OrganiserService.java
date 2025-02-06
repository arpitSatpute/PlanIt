package com.teamarc.planit.services;


import com.teamarc.planit.entity.Organiser;
import com.teamarc.planit.repository.OrganiserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganiserService {
    private final OrganiserRepository organiserRepository;

    public OrganiserService(OrganiserRepository organiserRepository) {
        this.organiserRepository = organiserRepository;
    }

    public Organiser createNewOrganiser(Organiser organiser) {
        return organiserRepository.save(organiser);
    }
}
