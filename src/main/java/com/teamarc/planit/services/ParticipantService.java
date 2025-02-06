package com.teamarc.planit.services;

import com.teamarc.planit.dto.ParticipantDTO;
import com.teamarc.planit.entity.Participant;
import com.teamarc.planit.entity.User;
import com.teamarc.planit.exceptions.ResourceNotFoundException;
import com.teamarc.planit.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ModelMapper modelMapper;
    private final ParticipantRepository participantRepository;

    public void createParticipant(User user, Long userId) {
        Participant participant = new Participant();
        participant.setUser(user);
        participantRepository.save(participant);
    }



    // Update

    public ParticipantDTO updateParticipant(Long participantId, Map<String, Object> updates) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + participantId));

        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(Participant.class, field);
            if (fieldToBeUpdated != null) {
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, participant, value);
            }
        });
        return modelMapper.map(participantRepository.save(participant), ParticipantDTO.class);
    }

    // Get
    public ParticipantDTO getParticipantById(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));
        return modelMapper.map(participant, ParticipantDTO.class);
    }


    // GetAll

    public Page<ParticipantDTO> getAllParticipants( Pageable pageable) {

        Page<Participant> participant = participantRepository.findAll(pageable);
        return participant.map(participants -> modelMapper.map(participant, ParticipantDTO.class));


    }

    private Participant getCurrentParticipant() {

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return participantRepository.findByUser(user)
                    .orElseThrow(() -> new ResourceNotFoundException("Participant not associated with user with id: " + user.getId()));

    }



}

