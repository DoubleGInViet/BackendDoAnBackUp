package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.dtos.TicketClassDTO;
import com.duy.BackendDoAn.models.TicketClass;
import com.duy.BackendDoAn.services.TicketClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/ticketClass")
@RestController
public class TicketClassController {
    private final TicketClassService ticketClassService;
    @PostMapping
    public ResponseEntity<TicketClass> createTicket(@Valid @RequestBody TicketClassDTO ticketClassDTO) throws Exception {
        TicketClass newTicketClass = ticketClassService.addTicketClass(ticketClassDTO);
        return ResponseEntity.ok(newTicketClass);
    }
}
