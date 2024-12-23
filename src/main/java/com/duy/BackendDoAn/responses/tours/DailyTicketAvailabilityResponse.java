package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.DailyTicketAvailability;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyTicketAvailabilityResponse {
    private Long id;
    private String name;
    private Long price;

    @JsonProperty("available_ticket")
    private Long availableTicket;

    public static DailyTicketAvailabilityResponse fromDailyTicket(DailyTicketAvailability dailyTicketAvailability) {
        return DailyTicketAvailabilityResponse.builder()
                .id(dailyTicketAvailability.getTicketClass().getId())
                .name(dailyTicketAvailability.getTicketClass().getName())
                .price(dailyTicketAvailability.getTicketClass().getPrice())
                .availableTicket(dailyTicketAvailability.getAvailableTicket())
                .build();
    }
}
