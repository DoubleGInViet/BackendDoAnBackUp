package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.dtos.bookingVehicleDTOs.BookingVehicleDTO;
import com.duy.BackendDoAn.models.BookingVehicle;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomListResponse;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomResponse;
import com.duy.BackendDoAn.responses.bookingVehicles.BookingVehicleListResponse;
import com.duy.BackendDoAn.responses.bookingVehicles.BookingVehicleResponse;
import com.duy.BackendDoAn.services.BookingVehicleService;
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
@RequestMapping("/bookingVehicle")
@RestController
public class BookingVehicleController {
    private final BookingVehicleService bookingVehicleService;
    private final EmailService emailService;
    @PostMapping
    public ResponseEntity<BookingVehicleResponse> createBooking(@Valid @RequestBody BookingVehicleDTO bookingVehicleDTO) throws Exception {
        BookingVehicle bookingVehicle = bookingVehicleService.createBooking(bookingVehicleDTO);
        BookingVehicleResponse bookingVehicleResponse = BookingVehicleResponse.fromBooking(bookingVehicle);
        emailService.sendBookingVehicleMessage(bookingVehicleResponse);
        return ResponseEntity.ok(bookingVehicleResponse);
    }

    @GetMapping("/{bookingVehicleId}")
    public ResponseEntity<BookingVehicleResponse> getBookingById(@PathVariable("bookingVehicleId") String id) {
        BookingVehicle bookingVehicle = bookingVehicleService.getBookingById(id);
        return ResponseEntity.ok(BookingVehicleResponse.fromBooking(bookingVehicle));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<BookingVehicleListResponse> getBookingByUser(@PathVariable("userId") Long id) throws Exception {
        PageRequest pageRequest = PageRequest.of(
                0, 10000,
                Sort.by("id").ascending()
        );

        Page<BookingVehicleResponse> bookingVehicleResponses = bookingVehicleService.getBookingByUser(id, pageRequest);
        List<BookingVehicleResponse> responses = bookingVehicleResponses.getContent();
        int totalPages = bookingVehicleResponses.getTotalPages();
        return ResponseEntity.ok(BookingVehicleListResponse.builder()
                .response(responses)
                .totalPages(totalPages)
                .build());
    }
}
