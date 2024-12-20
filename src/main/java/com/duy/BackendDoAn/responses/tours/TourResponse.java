package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.Tour;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourResponse {
    private Long id;
    private String name;
    private List<TourImageResponse> images;
    private List<TicketClassResponse> tickets;
    private ReviewTourResponse review;

    public static TourResponse fromTour(Tour tour) {
        return TourResponse.builder()
                .id(tour.getId())
                .name(tour.getName())
                .images(
                        tour.getTourImages().stream()
                                .map(TourImageResponse::fromTourImages)
                                .collect(Collectors.toList())
                )
                .tickets(
                        tour.getTicketClasses().stream()
                                .map(TicketClassResponse::fromTicket)
                                .collect(Collectors.toList())
                )
                .review(ReviewTourResponse.fromReviews(tour))
                .build();
    }
}
