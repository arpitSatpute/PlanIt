package com.teamarc.planit.services;

import com.teamarc.planit.dto.TicketDTO;
import com.teamarc.planit.entity.Event;
import com.teamarc.planit.entity.Participant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventBookingService {
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;

    public TicketDTO bookEvent(Event event, Participant currentParticipant) {
        if(!(event.getCapacity()<= (long) event.getParticipants().size())){
            throw new RuntimeException("Event is full");
        }
        paymentService.createNewPayment(event);
        paymentService.processPayment(event, currentParticipant.getId());
        return modelMapper.map(event, TicketDTO.class);
    }

}
