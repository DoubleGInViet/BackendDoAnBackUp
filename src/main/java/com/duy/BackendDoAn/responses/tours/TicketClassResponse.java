package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.TicketClass;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketClassResponse {
    private Long id;
    private String name;
    private Long adultPrice;
    private Long childrenPrice;
    private Long availableTicket;

    public static TicketClassResponse fromTicket(TicketClass ticketClass) {
        return TicketClassResponse.builder()
                .id(ticketClass.getId())
                .name(ticketClass.getName())
                .adultPrice(ticketClass.getAdult_price())
                .childrenPrice(ticketClass.getChildren_price())
                .availableTicket(ticketClass.getAvailable_ticket())
                .build();
    }
}
