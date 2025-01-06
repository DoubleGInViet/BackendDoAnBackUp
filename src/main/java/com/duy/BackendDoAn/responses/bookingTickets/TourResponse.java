package com.duy.BackendDoAn.responses.bookingTickets;

import com.duy.BackendDoAn.models.Tour;
import com.duy.BackendDoAn.models.TourSchedule;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourResponse {
    private Long id;

    @JsonProperty("tour_name")
    private String tourName;

    @JsonProperty("city")
    private String cityName;

    @JsonProperty("happen_time")
    private String happenTime;

    public static TourResponse fromTour(TourSchedule tourSchedule){
        return TourResponse.builder()
                .id(tourSchedule.getTour().getId())
                .tourName(tourSchedule.getTour().getName())
                .cityName(tourSchedule.getTour().getAttraction().getCity().getCity_name())
                .happenTime(tourSchedule.getStartTime().format(DateTimeFormatter.ofPattern("hh:mm")))
                .build();
    }
}
