package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.dtos.TicketClassDTO;
import com.duy.BackendDoAn.models.TicketClass;
import com.duy.BackendDoAn.models.Tour;
import com.duy.BackendDoAn.repositories.TicketClassRepository;
import com.duy.BackendDoAn.repositories.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketClassService {
    private final TourRepository tourRepository;
    private final TicketClassRepository ticketClassRepository;
    public TicketClass addTicketClass(TicketClassDTO ticketClassDTO) throws Exception {
        Tour tour = tourRepository.findById(ticketClassDTO.getTour()).orElseThrow(()-> new Exception("Tour not found!!"));
        TicketClass ticketClass = TicketClass.builder()
                .name(ticketClassDTO.getName())
                .price(ticketClassDTO.getPrice())
                .maxAmount(ticketClassDTO.getMaxAmount())
                .description(ticketClassDTO.getDescription())
                .tour(tour)
                .active(true)
                .build();
        return ticketClassRepository.save(ticketClass);
    }
}
