package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.dtos.BookingTicketDTO;
import com.duy.BackendDoAn.models.BookingTicket;
import com.duy.BackendDoAn.responses.bookingTickets.BookingTicketListResponse;
import com.duy.BackendDoAn.responses.bookingTickets.BookingTicketResponse;
import com.duy.BackendDoAn.services.BookingTicketService;
import com.duy.BackendDoAn.services.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user/{id}")
    public ResponseEntity<BookingTicketListResponse> getBookingByUser(@PathVariable("id") Long userId) throws Exception {
        PageRequest pageRequest = PageRequest.of(
                0, 1000,
                Sort.by("id").ascending()
        );

        Page<BookingTicketResponse> bookingTicketResponses = bookingTicketService.getBookingByUser(userId, pageRequest);
        List<BookingTicketResponse> list = bookingTicketResponses.getContent();
        int totalPages = bookingTicketResponses.getTotalPages();
        return ResponseEntity.ok(BookingTicketListResponse.builder()
                .bookingTicketResponses(list)
                .totalPages(totalPages)
                .build()
        );
    }
}
