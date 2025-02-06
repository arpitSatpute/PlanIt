package com.teamarc.planit.services;

import com.teamarc.planit.dto.VenueDTO;
import com.teamarc.planit.entity.Venue;
import com.teamarc.planit.exceptions.ResourceNotFoundException;
import com.teamarc.planit.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {


    private final ModelMapper modelMapper;
    private final VenueRepository venueRepository;

    public void createVenue(VenueDTO venueDTO) {
        Venue venue = modelMapper.map(venueDTO, Venue.class);
        venueRepository.save(venue);

    }

    public VenueDTO getVenueById(Long id) {
        return modelMapper.map(venueRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Venue Not Found")), VenueDTO.class);
    }

    public List<VenueDTO> getAllVenue() {
        return venueRepository.findAll()
                .stream().map(venue -> modelMapper.map(venue, VenueDTO.class)).toList();
    }

    public String deleteVenue(Long id) {
        Venue venue = venueRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Venue Not Found"));
        venueRepository.delete(venue);
        return "Venue deleted successfully";
    }

}
