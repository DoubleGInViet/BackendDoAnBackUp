package com.duy.BackendDoAn.responses.bookingTickets;

import com.duy.BackendDoAn.models.BookedTicket;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookedTicketResponse {

    private Long quantity;

    @JsonProperty("ticket_class")
    private String ticketClass;

    @JsonProperty("unit_price")
    private Long unitPrice;


    @JsonProperty("price_mul_amount")
    private Long priceMulAmount;

    public static BookedTicketResponse fromBookedTicket(BookedTicket bookedTicket) {
        return BookedTicketResponse.builder()
                .ticketClass(bookedTicket.getTicketClass().getName())
                .quantity(bookedTicket.getQuantity())
                .unitPrice(bookedTicket.getTicketClass().getPrice())
                .priceMulAmount(bookedTicket.getPriceWithQuantity())
                .build();
    }
}
