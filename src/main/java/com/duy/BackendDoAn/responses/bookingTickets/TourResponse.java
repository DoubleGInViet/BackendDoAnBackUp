package com.duy.BackendDoAn.responses.bookingTickets;

import com.duy.BackendDoAn.models.Tour;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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

    public static TourResponse fromTour(Tour tour){
        return TourResponse.builder()
                .id(tour.getId())
                .tourName(tour.getName())
                .cityName(tour.getAttraction().getCity().getCity_name())
                .build();
    }
}
