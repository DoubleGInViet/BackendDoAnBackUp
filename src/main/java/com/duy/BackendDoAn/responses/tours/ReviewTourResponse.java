package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.ReviewTour;
import com.duy.BackendDoAn.models.Tour;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewTourResponse {
    @JsonProperty("total_reviews")
    private Long totalReviews;

    @JsonProperty("average_rating")
    private Float averageRating;

    @JsonProperty("review_breakdown")
    private Map<String, Long> reviewBreakdown;

    @JsonProperty("recent_reviews")
    private List<UserReviewTourResponse> userReviewTourResponses;

    public static ReviewTourResponse fromReviews(Tour tour) {
        ReviewTourResponse response = new ReviewTourResponse();
        response.totalReviews = tour.getReviewCount();
        response.averageRating = tour.getRating();

        List<ReviewTour> reviews = tour.getReviewTours();

        response.userReviewTourResponses = reviews.stream().map(UserReviewTourResponse::fromReview).collect(Collectors.toList());
        Map<String, Long> reviewBreakdown = new HashMap<>();
        reviewBreakdown.put("5_star", reviews.stream().filter(r -> r.getRating() == 5).count());
        reviewBreakdown.put("4_star", reviews.stream().filter(r -> r.getRating() == 4).count());
        reviewBreakdown.put("3_star", reviews.stream().filter(r -> r.getRating() == 3).count());
        reviewBreakdown.put("2_star", reviews.stream().filter(r -> r.getRating() == 2).count());
        reviewBreakdown.put("1_star", reviews.stream().filter(r -> r.getRating() == 1).count());
        response.reviewBreakdown = reviewBreakdown;
        return response;
    }
}
