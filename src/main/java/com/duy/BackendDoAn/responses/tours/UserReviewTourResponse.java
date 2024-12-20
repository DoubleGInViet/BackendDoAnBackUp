package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.ReviewTour;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReviewTourResponse {
    private Long rating;

    private String user;

    private String date;

    private String comment;

    public static UserReviewTourResponse fromReview(ReviewTour reviewTour) {
        return UserReviewTourResponse.builder()
                .rating(reviewTour.getRating())
                .user(reviewTour.getUser().getName())
                .comment(reviewTour.getComment())
                .date(reviewTour.getReview_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }
}
