package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.DailyTicketAvailability;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyTicketAvailabilityResponse {
    private Long id;
    private String name;
    private Long price;
    @JsonProperty("happen_date")
    private String happenDate;
    @JsonProperty("available_ticket")
    private Long availableTicket;

    public static DailyTicketAvailabilityResponse fromDailyTicket(DailyTicketAvailability dailyTicketAvailability) {
        return DailyTicketAvailabilityResponse.builder()
                .id(dailyTicketAvailability.getTicketClass().getId())
                .name(dailyTicketAvailability.getTicketClass().getName())
                .price(dailyTicketAvailability.getTicketClass().getPrice())
                .availableTicket(dailyTicketAvailability.getAvailableTicket())
                .happenDate(dailyTicketAvailability.getHappenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
