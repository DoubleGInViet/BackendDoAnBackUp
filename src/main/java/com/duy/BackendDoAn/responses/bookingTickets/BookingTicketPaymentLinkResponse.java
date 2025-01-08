package com.duy.BackendDoAn.responses.bookingTickets;

import com.duy.BackendDoAn.models.BookingTicket;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingTicketPaymentLinkResponse {
    private BookingTicketResponse bookingTicket;
    private String payment;
}
