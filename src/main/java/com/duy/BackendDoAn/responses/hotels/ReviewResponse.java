package com.duy.BackendDoAn.responses.hotels;

import com.duy.BackendDoAn.models.Hotel;
import com.duy.BackendDoAn.models.ReviewHotel;
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
public class ReviewResponse {
    @JsonProperty("total_reviews")
    private Long totalReviews;

    @JsonProperty("average_rating")
    private Float averageRating;

    @JsonProperty("review_breakdown")
    private Map<String, Long> reviewBreakdown;

    @JsonProperty("recent_reviews")
    private List<UserReviewResponse> userReviewResponses;

    public static ReviewResponse fromReviews(Hotel hotel){
        ReviewResponse response = new ReviewResponse();

        List<ReviewHotel> reviews = hotel.getReviews();
        response.totalReviews = (long) reviews.size();
        Map<String, Long> reviewBreakdown = new HashMap<>();
        double averageRating = reviews.stream()
                .mapToDouble(ReviewHotel::getRating)
                .average()
                .orElse(0.0); // Nếu không có đánh giá, trả về 0.0

// Làm tròn sau 1 số thập phân
        float roundedAverageRating = (float) (Math.round(averageRating * 10) / 10.0);

        response.averageRating = roundedAverageRating;

        reviewBreakdown.put("5_star", reviews.stream().filter(r -> r.getRating() == 5).count());
        reviewBreakdown.put("4_star", reviews.stream().filter(r -> r.getRating() == 4).count());
        reviewBreakdown.put("3_star", reviews.stream().filter(r -> r.getRating() == 3).count());
        reviewBreakdown.put("2_star", reviews.stream().filter(r -> r.getRating() == 2).count());
        reviewBreakdown.put("1_star", reviews.stream().filter(r -> r.getRating() == 1).count());
        response.reviewBreakdown = reviewBreakdown;

        response.userReviewResponses = reviews.stream().map(UserReviewResponse::fromUserReview).collect(Collectors.toList());
        return response;
    }
}
