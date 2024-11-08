package com.duy.BackendDoAn.controllers;

import com.duy.BackendDoAn.dtos.BookingRoomDTO;
import com.duy.BackendDoAn.models.BookingRoom;
import com.duy.BackendDoAn.responses.BookingRoomListResponse;
import com.duy.BackendDoAn.responses.BookingRoomResponse;
import com.duy.BackendDoAn.services.BookingRoomService;
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

    @PostMapping
    public ResponseEntity<BookingRoomResponse> createBooking(@Valid @RequestBody BookingRoomDTO bookingRoomDTO) throws Exception {
        BookingRoom bookingRoom = bookingRoomService.createBooking(bookingRoomDTO);
        BookingRoomResponse bookingRoomResponse = BookingRoomResponse.fromBookingRoom(bookingRoom);
        return ResponseEntity.ok(bookingRoomResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingRoomResponse> updateStatus(@PathVariable Long id, @RequestBody BookingRoomDTO bookingRoomDTO) throws Exception {
        BookingRoom bookingRoom = bookingRoomService.updateStatusBooking(id, bookingRoomDTO);
        return ResponseEntity.ok(BookingRoomResponse.fromBookingRoom(bookingRoom));
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
    public ResponseEntity<BookingRoomResponse> getBookingById(@PathVariable("bookingRoomId") long id) {
        BookingRoom bookingRoom = bookingRoomService.getBookingById(id);
        return ResponseEntity.ok(BookingRoomResponse.fromBookingRoom(bookingRoom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable long id) throws Exception {
        bookingRoomService.deleteBookingRoom(id);
        return ResponseEntity.ok("Delete booking successfully");
    }
}
