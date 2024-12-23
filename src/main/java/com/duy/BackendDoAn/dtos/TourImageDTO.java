package com.duy.BackendDoAn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourImageDTO {
    @JsonProperty("tour_id")
    private Long tour;

    @JsonProperty("image_url")
    private String imageUrl;
}
