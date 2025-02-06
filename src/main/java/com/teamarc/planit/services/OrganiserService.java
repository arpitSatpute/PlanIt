package com.teamarc.planit.services;


import com.teamarc.planit.dto.OrganiserDTO;
import com.teamarc.planit.entity.Organiser;
import com.teamarc.planit.entity.Participant;
import com.teamarc.planit.exceptions.ResourceNotFoundException;
import com.teamarc.planit.repository.OrganiserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class OrganiserService {
    private final OrganiserRepository organiserRepository;
    private final ModelMapper modelMapper;

    public OrganiserService(OrganiserRepository organiserRepository, ModelMapper modelMapper) {
        this.organiserRepository = organiserRepository;
        this.modelMapper = modelMapper;
    }

    public Organiser createNewOrganiser(Organiser organiser) {
        return organiserRepository.save(organiser);
    }

    public OrganiserDTO getOrganiserById(Long id) {
        return modelMapper.map(organiserRepository.findById(id).orElse(null), OrganiserDTO.class);
    }

    public List<OrganiserDTO> getAllOrganiser() {
        return organiserRepository.findAll()
                .stream().map(organiser -> modelMapper.map(organiser, OrganiserDTO.class)).toList();
    }

    public OrganiserDTO updateOrganiser(Long id, Map<String, Object> updates) {
        Organiser organiser = organiserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organiser not found with id: " + id));
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(Participant.class, field);
            if (fieldToBeUpdated != null) {
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, organiser, value);
            }
        });
        return modelMapper.map(organiserRepository.save(organiser), OrganiserDTO.class);
    }



}
