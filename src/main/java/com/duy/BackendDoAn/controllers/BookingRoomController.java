package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.dtos.BookingRoomDTO;
import com.duy.BackendDoAn.models.BookingRoom;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomPaymentLinkResponse;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomListResponse;
import com.duy.BackendDoAn.responses.bookingRooms.BookingRoomResponse;
import com.duy.BackendDoAn.services.BookingRoomService;
import com.duy.BackendDoAn.services.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/bookingRoom")
@RestController
public class BookingRoomController {
    private final BookingRoomService bookingRoomService;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<BookingRoomResponse> createBooking(@Valid @RequestBody BookingRoomDTO bookingRoomDTO) throws Exception {
        BookingRoom bookingRoom = bookingRoomService.createBooking(bookingRoomDTO);
        BookingRoomResponse bookingRoomResponse = BookingRoomResponse.fromBooking(bookingRoom);
        emailService.sendBookingEmail(bookingRoomResponse);
        return ResponseEntity.ok(bookingRoomResponse);
    }

    @PostMapping("/online")
    public ResponseEntity<BookingRoomPaymentLinkResponse> createBookingPayment(@Valid @RequestBody BookingRoomDTO bookingRoomDTO, HttpServletRequest request) throws Exception {
        BookingRoomPaymentLinkResponse booking = bookingRoomService.createBookingPayment(bookingRoomDTO, request);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingRoomResponse> updateStatus(@PathVariable String id, @RequestBody BookingRoomDTO bookingRoomDTO) throws Exception {
        BookingRoom bookingRoom = bookingRoomService.updateStatusBooking(id, bookingRoomDTO);
        return ResponseEntity.ok(BookingRoomResponse.fromBooking(bookingRoom));
    }

    @GetMapping
    public ResponseEntity<BookingRoomListResponse> getAllBooking(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) throws Exception {
        String extractedToken = authorizationHeader.substring(7);
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("id").ascending()
        );
        Page<BookingRoomResponse> bookingRoomResponsePage = bookingRoomService.getAllBookingRoom(extractedToken, pageRequest);
        int totalPages = bookingRoomResponsePage.getTotalPages();
        List<BookingRoomResponse> bookingRoomResponses = bookingRoomResponsePage.getContent();
        return ResponseEntity.ok(BookingRoomListResponse.builder()
                .bookingRoom(bookingRoomResponses)
                .totalPages(totalPages)
                .build());
    }

    @GetMapping("/{bookingRoomId}")
    public ResponseEntity<BookingRoomResponse> getBookingById(@PathVariable("bookingRoomId") String id) {
        BookingRoom bookingRoom = bookingRoomService.getBookingById(id);
        return ResponseEntity.ok(BookingRoomResponse.fromBooking(bookingRoom));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<BookingRoomListResponse> getBookingByUser(@PathVariable("userId") Long id) throws Exception {
        PageRequest pageRequest = PageRequest.of(
                0, 1000,
                Sort.by("id").ascending()
        );

        Page<BookingRoomResponse> bookingRoomResponses = bookingRoomService.getBookingByUser(id, pageRequest);
        List<BookingRoomResponse> responses = bookingRoomResponses.getContent();
        int totalPages = bookingRoomResponses.getTotalPages();
        return ResponseEntity.ok(BookingRoomListResponse.builder()
                .bookingRoom(responses)
                .totalPages(totalPages)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable String id) throws Exception {
        bookingRoomService.deleteBookingRoom(id);
        return ResponseEntity.ok("Delete booking successfully");
    }

    @GetMapping("/status")
    public ResponseEntity<String> getPaymentStatus(@RequestParam("id") String id) throws Exception {
        String status = bookingRoomService.getPaymentStatus(id);
        return ResponseEntity.ok(status);
    }
}
