package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.dtos.BookingTicketDTO;
import com.duy.BackendDoAn.models.BookingTicket;
import com.duy.BackendDoAn.responses.bookingTickets.BookingTicketResponse;
import com.duy.BackendDoAn.services.BookingTicketService;
import com.duy.BackendDoAn.services.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/bookingTicket")
@RestController
public class BookingTicketController {
    private final BookingTicketService bookingTicketService;
    private final EmailService emailService;
    @PostMapping
    public ResponseEntity<BookingTicketResponse> createBooking(@Valid @RequestBody BookingTicketDTO bookingTicketDTO) throws Exception {
        BookingTicket newBookingTicket = bookingTicketService.createBookingTicket(bookingTicketDTO);
        BookingTicketResponse bookingTicketResponse = BookingTicketResponse.fromBooking(newBookingTicket);
        emailService.sendBookingTicketMessage(bookingTicketResponse);
        return ResponseEntity.ok(bookingTicketResponse);
    }
}
