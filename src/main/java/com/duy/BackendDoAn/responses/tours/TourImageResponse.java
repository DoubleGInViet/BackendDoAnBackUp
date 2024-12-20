package com.duy.BackendDoAn.responses.tours;

import com.duy.BackendDoAn.models.TourImage;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourImageResponse {
    private Long id;
    private String url;

    public static TourImageResponse fromTourImages(TourImage tourImage) {
        return TourImageResponse.builder()
                .id(tourImage.getId())
                .url(tourImage.getImage_url())
                .build();
    }
}
