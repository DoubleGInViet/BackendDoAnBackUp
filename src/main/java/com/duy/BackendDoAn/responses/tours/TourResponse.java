package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.Tour;
import com.duy.BackendDoAn.responses.tours.ReviewTourResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private String description;
    private List<TourImageResponse> images;

    @JsonProperty("tour_schedule_responses")
    private List<TourScheduleResponse> tourScheduleResponses;
    private ReviewTourResponse review;

    public static TourResponse fromTour(Tour tour) {
        return TourResponse.builder()
                .id(tour.getId())
                .name(tour.getName())
                .images(
                        tour.getTourImages() != null
                                ? tour.getTourImages().stream()
                                .map(TourImageResponse::fromTourImages)
                                .collect(Collectors.toList())
                                : new ArrayList<>()
                )
                .tourScheduleResponses(
                        tour.getTourSchedules() != null

                                ? tour.getTourSchedules().stream()
                                .filter(tourSchedule -> (tourSchedule.getHappenDate().isAfter(LocalDate.now())
                                        || tourSchedule.getHappenDate().isEqual(LocalDate.now())))
                                .map(TourScheduleResponse::fromTourSchedule)
                                .collect(Collectors.toList())
                                : new ArrayList<>()
                )
                .review(
                        (tour.getReviewTours() != null)
                                ? ReviewTourResponse.fromReviews(tour)
                                : new ReviewTourResponse()
                )
                .build();
    }
}
