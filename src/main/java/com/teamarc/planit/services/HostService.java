package com.teamarc.planit.services;

import com.teamarc.planit.entity.Organiser;
import com.teamarc.planit.entity.RequestOrganiser;
import com.teamarc.planit.entity.enums.SessionStatus;
import com.teamarc.planit.exceptions.ResourceNotFoundException;
import com.teamarc.planit.repository.HostRepository;
import com.teamarc.planit.repository.OrganiserRepository;
import com.teamarc.planit.repository.RequestOrganiserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HostService {

    private final HostRepository hostRepository;
    private final OrganiserRepository organiserRepository;
    private final RequestOrganiserRepository requestOrganiserRepository;

    public UUID requestSession(Long organiserId, String date) {

        Organiser organiser = organiserRepository.findById(organiserId).orElseThrow(()-> new ResourceNotFoundException("Organiser not found with id: " + organiserId));
        RequestOrganiser requestOrganiser = new RequestOrganiser();
        requestOrganiser.setOrganiser(organiser);
        requestOrganiser.setSessionId(UUID.randomUUID());
        requestOrganiser.setDate(date);
        requestOrganiser.setStatus(SessionStatus.valueOf("PENDING"));
        requestOrganiserRepository.save(requestOrganiser);
        return requestOrganiser.getSessionId();

    }


    public UUID getSessionId(Long requestOrganiserId) {
        RequestOrganiser requestOrganiser = requestOrganiserRepository.findById(requestOrganiserId).orElseThrow(()-> new ResourceNotFoundException("Session not found for organiser with id: " + requestOrganiserId));
        return requestOrganiser.getSessionId();
    }

    public String deleteSession(Long requestOrganiserId) {
        RequestOrganiser requestOrganiser = requestOrganiserRepository.findById(requestOrganiserId).orElseThrow(()-> new ResourceNotFoundException("Session not found for organiser with id: " + requestOrganiserId));
        requestOrganiserRepository.delete(requestOrganiser);
        return "Session cancelled successfully";
    }
}
