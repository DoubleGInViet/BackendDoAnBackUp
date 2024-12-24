package com.duy.BackendDoAn.responses.bookingTickets;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingTicketListResponse {
    private List<BookingTicketResponse> bookingTicketResponses;
    private int totalPages;
}
